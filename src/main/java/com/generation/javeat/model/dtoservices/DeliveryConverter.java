package com.generation.javeat.model.dtoservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javeat.model.dto.delivery.DeliveryDtoResponse;
import com.generation.javeat.model.dto.delivery.DeliveryInstRqstDto;
import com.generation.javeat.model.dto.deliveryStat.DeliverySummaryByWeekDto;
import com.generation.javeat.model.entities.Delivery;
import com.generation.javeat.model.entities.Dish;
import com.generation.javeat.model.entities.DishToDelivery;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.entities.User;
import com.generation.javeat.model.repositories.DeliveryRepository;
import com.generation.javeat.model.repositories.DishRepository;
import com.generation.javeat.model.repositories.DishToDeliveryRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;
import com.generation.javeat.model.repositories.UserRepository;
import static  com.generation.javeat.utils.Utils.*;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



@Service
public class DeliveryConverter 
{
    @Autowired
    DeliveryRepository dRepo;


    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    DishRepository repoD;

    @Autowired
    UserRepository uRepo;

    @Autowired
    DishConverter conv; 

    @Autowired
    DishToDeliveryRepository DTRepo; 

    // private Integer idRestaurant;
    // private Integer idUser;
    // private LocalDateTime expected_arrival;
    // private String paymentMethod;
    // private String notes;
    // private List<Integer> dishesId;

    public Delivery deliveryDtoRqstDelivery(DeliveryInstRqstDto del)
    {
        User user = uRepo.findById(del.getIdUser()).get();
        Restaurant restaurant = rRepo.findById(del.getIdRestaurant()).get();

        String[] parts = del.getExpected_arrival().toString().split(":");

        Delivery newDelivery = Delivery
                .builder()
                .expected_arrival(LocalDateTime.now().withHour(Integer.parseInt(parts[0])).withMinute(Integer.parseInt(parts[1])))
                .distance(calculateDistanceToRestaurant(restaurant,user.getPositionX(),user.getPositionY()))
                .paymentMethod(del.getPaymentMethod())
                .notes(del.getNotes())
                .user(user)
                .restaurant(restaurant)
                .dishesDeliveries(dishesByIds(del.getDishes()))
                .build();

        newDelivery.getDishesDeliveries().stream().forEach(d -> d.setDelivery(newDelivery));
        return newDelivery;        

    }

    public DeliveryDtoResponse deliveryDtoResponse(Delivery d)
    {
        return DeliveryDtoResponse
                .builder()
                .orderId(d.getId())
                .paymentMethod(d.getPaymentMethod())
                .notes(d.getNotes())
                .expectedArrival(d.getExpected_arrival())
                .dishes(d.getDishesDeliveries().stream().toList())
                .dishesPrice(d.getDishesPrice())
                .riderRevenue(d.getRiderRevenue())
                .totalPrice(d.getTotalPrice())
                .build();
    }


    public Set<DishToDelivery> dishesByIds(Map<Integer, Integer> dishes)
    { 
        Set<DishToDelivery> newSet = new HashSet<DishToDelivery>();

        for (Integer key : dishes.keySet()) 
        {
            DishToDelivery dtd = new DishToDelivery();
            dtd.setDish(repoD.findById(key).get());
            dtd.setQuantity(dishes.get(key));
            newSet.add(dtd);
            DTRepo.save(dtd);
        
        }    
        return newSet;
    }

    public List<DeliverySummaryByWeekDto> convertToOrderSummaryByWeek(List<Delivery> deliveries) {
        Map<Integer, DeliverySummaryByWeekDto> summaryByWeek = new HashMap<>();

        for (Delivery delivery : deliveries) {
            int weekOfYear = delivery.getExpected_arrival().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

            DeliverySummaryByWeekDto weeklySummary = summaryByWeek.computeIfAbsent(weekOfYear, k -> new DeliverySummaryByWeekDto());
            weeklySummary.setWeekOfYear("Week: " + weekOfYear);
            weeklySummary.setTotalEarnings(weeklySummary.getTotalEarnings() + delivery.getTotalPrice());

            for (DishToDelivery dishToDelivery : delivery.getDishesDeliveries()) {
                Dish dish = dishToDelivery.getDish();
                if (dish != null) {
                    weeklySummary.getDishesOrdered().merge(dish.getName(), dishToDelivery.getQuantity(), Integer::sum);
                }
            }
        }

        return new ArrayList<>(summaryByWeek.values())
            .stream()
            .sorted(Comparator.comparing(DeliverySummaryByWeekDto::getWeekOfYear))
            .collect(Collectors.toList());
    }
}
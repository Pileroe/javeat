package com.generation.javeat.model.dto.delivery;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DelveryInstRqstDto 
{
    private Integer idRestaurant;
    private Integer idUser;
    private LocalDateTime expected_arrival;
    private String paymentMethod;
    private String notes;
    private List<Integer> dishesId;
}

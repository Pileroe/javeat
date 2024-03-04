package com.generation.javeat.model.dto.deliveryStat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeliverySummaryByWeekDto {

    private String weekOfYear;
    private double totalEarnings;
    private Map<String, Integer> dishesOrdered = new HashMap<>();
}

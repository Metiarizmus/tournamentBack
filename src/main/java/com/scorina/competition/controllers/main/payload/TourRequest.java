package com.scorina.competition.controllers.main.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class TourRequest {
    @NotNull
    private String teamName;

    @NotNull
    private String tourName;

    @NotNull
    private Integer teamPoints;
}

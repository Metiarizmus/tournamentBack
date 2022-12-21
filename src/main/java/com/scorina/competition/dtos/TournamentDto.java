package com.scorina.competition.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Calendar;

@Data
public class TournamentDto {
    @NotBlank
    private Calendar dateFrom;

    @NotBlank
    private Calendar dateTo;

    @NotBlank
    private Calendar dateRegistrationFrom;

    @NotBlank
    private Calendar dateRegistrationTo;

    @NotBlank
    private Integer countTour;


}

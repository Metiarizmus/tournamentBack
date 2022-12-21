package com.scorina.competition.controllers.main.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TournamentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String dateFrom;

    @NotBlank
    private String dateTo;

    @NotBlank
    private String dateRegistrationFrom;

    @NotBlank
    private String dateRegistrationTo;

    @NotNull
    private Integer countTour;

    @NotNull
    private TourRequest[] tourRequests;

    @Data
    public static class TourRequest {
        @NotBlank
        private String date;
        @NotBlank
        private String tourName;
    }
}



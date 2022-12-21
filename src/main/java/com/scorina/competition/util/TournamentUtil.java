package com.scorina.competition.util;

import com.scorina.competition.controllers.main.payload.TournamentRequest;
import com.scorina.competition.models.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class TournamentUtil {

    @Autowired
    private DateConverter dateConverter;

    public Tournament createTournament(TournamentRequest request) {

        try {
            return Tournament.builder()
                    .dateFrom(dateConverter.convert(request.getDateFrom()))
                    .dateTo(dateConverter.convert(request.getDateTo()))
                    .dateRegistrationFrom(dateConverter.convert(request.getDateRegistrationFrom()))
                    .dateRegistrationTo(dateConverter.convert(request.getDateRegistrationTo()))
                    .countTour(request.getCountTour())
                    .name(request.getName())
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}

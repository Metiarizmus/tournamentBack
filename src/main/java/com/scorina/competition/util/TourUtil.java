package com.scorina.competition.util;

import com.scorina.competition.controllers.main.payload.TournamentRequest;
import com.scorina.competition.models.Tour;
import com.scorina.competition.models.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class TourUtil {

    @Autowired
    private DateConverter dateConverter;


    public Tour createTour(TournamentRequest.TourRequest request, Tournament tournament) {

        try {
            return Tour.builder()
                    .date(dateConverter.convert(request.getDate()))
                    .tourName(request.getTourName())
                    .tournament(tournament)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;

    }

}

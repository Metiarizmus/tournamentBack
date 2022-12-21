package com.scorina.competition.services;

import com.scorina.competition.controllers.main.payload.TourRequest;
import com.scorina.competition.repositories.TourRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepo tourRepo;

    public void createTour(TourRequest tourRequest) {



    }
}

package com.scorina.competition.repositories;

import com.scorina.competition.models.Tour;
import com.scorina.competition.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepo extends JpaRepository<Tour, Integer> {

    List<Tour> findAllByTournament(Tournament tournament);

}

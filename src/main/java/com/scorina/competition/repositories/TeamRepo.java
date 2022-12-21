package com.scorina.competition.repositories;

import com.scorina.competition.models.Team;
import com.scorina.competition.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {

    List<Team> findAllByTournament(Tournament t);

}

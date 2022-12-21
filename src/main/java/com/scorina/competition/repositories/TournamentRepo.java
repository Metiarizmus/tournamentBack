package com.scorina.competition.repositories;

import com.scorina.competition.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM competition.tournament where :dateNow between date_registration_from and date_registration_to")
    Optional<Tournament> findTournamentByDateNowForRegistr(@Param("dateNow") String dateNow);

    @Query(nativeQuery = true, value = "SELECT * FROM competition.tournament where :dateNow between date_registration_from and date_registration_to or :dateNow between date_from and date_to")
    Optional<Tournament> findTournamentByDateNow(@Param("dateNow") String dateNow);

}

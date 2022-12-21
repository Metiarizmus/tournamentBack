package com.scorina.competition.repositories;

import com.scorina.competition.models.TourTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourTeamRepo extends JpaRepository<TourTeam, Integer> {

    @Query(nativeQuery = true, value = "select * from tour_team right join tour on tour.id = tour_team.tour_id " +
            "right join tournament on tournament.id = tour.tournament_id where tournament.id = :id")
    List<TourTeam> findAllByTournament_id(Long id);

    @Query(nativeQuery = true, value = "select * from tour_team \n" +
            "\tjoin team on team.id = tour_team.team_id\n" +
            "    join tour on tour.id = tour_team.tour_id\n" +
            "    join tournament on tournament.id = team.tournament_id\n" +
            "   where :dateNow between tournament.date_from and tournament.date_to\n" +
            "   and tour.tour_name = :tourName \n" +
            "   and team.team_name = :teamName")
    TourTeam getTourTeamByTourNameAndTeamName(@Param("dateNow") String dateNow, @Param("teamName") String teamName, @Param("tourName") String tourName);
}

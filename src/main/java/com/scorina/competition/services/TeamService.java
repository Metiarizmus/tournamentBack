package com.scorina.competition.services;

import com.scorina.competition.controllers.auth.payload.TeamRequest;
import com.scorina.competition.models.Team;
import com.scorina.competition.models.Tour;
import com.scorina.competition.models.TourTeam;
import com.scorina.competition.models.Tournament;
import com.scorina.competition.repositories.TeamRepo;
import com.scorina.competition.repositories.TourRepo;
import com.scorina.competition.repositories.TourTeamRepo;
import com.scorina.competition.repositories.TournamentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepo teamRepo;
    private final TourRepo tourRepo;
    private final TourTeamRepo tourTeamRepo;
    private final TournamentRepo tournamentRepo;

    @Transactional
    public boolean saveTeam(TeamRequest teamRequest) {
        Team team = new Team(teamRequest.getTeamName(), teamRequest.getGroupName(), teamRequest.getCountMembers());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(team.getDateRegistration());
        Optional<Tournament> tournament = tournamentRepo.findTournamentByDateNowForRegistr(format);

        if (tournament.isPresent()) {
            List<Tour> tourList = tourRepo.findAllByTournament(tournament.get());
            team.setTournament(tournament.get());
            teamRepo.save(team);

            List<TourTeam> tourTeams = new ArrayList<>();

            for (Tour q: tourList) {
                TourTeam tourTeam = new TourTeam();
                tourTeam.setPoints(0);
                tourTeam.setTeam(team);
                tourTeam.setTour(q);
                tourTeams.add(tourTeam);
            }

            tourTeamRepo.saveAllAndFlush(tourTeams);
            return true;
        }
        return false;
    }
}

package com.scorina.competition.services;

import com.scorina.competition.controllers.main.payload.TourRequest;
import com.scorina.competition.controllers.main.payload.TournamentRequest;
import com.scorina.competition.dtos.TournamentTableDto;
import com.scorina.competition.models.Tour;
import com.scorina.competition.models.TourTeam;
import com.scorina.competition.models.Tournament;
import com.scorina.competition.repositories.TeamRepo;
import com.scorina.competition.repositories.TourRepo;
import com.scorina.competition.repositories.TourTeamRepo;
import com.scorina.competition.repositories.TournamentRepo;
import com.scorina.competition.util.TourUtil;
import com.scorina.competition.util.TournamentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepo tournamentRepo;
    private final TeamRepo teamRepo;
    private final TourRepo tourRepo;
    private final TourTeamRepo tourTeamRepo;
    private final TournamentUtil tournamentUtil;
    private final TourUtil tourUtil;

    @Transactional
    public Tournament createTournament(TournamentRequest request) {

        Tournament tournament = tournamentUtil.createTournament(request);
        tournamentRepo.save(tournament);

        List<Tour> tourList = new ArrayList<>();
        for (TournamentRequest.@NotNull TourRequest q : request.getTourRequests()) {

            Tour tour = tourUtil.createTour(q, tournament);
            tourList.add(tour);
        }

        tourRepo.saveAllAndFlush(tourList);

        return tournament;
    }

    public TournamentTableDto getCurrentTournament() {

        Optional<Tournament> tournament = tournamentRepo.findTournamentByDateNow(getDateNow());

        if (tournament.isPresent()) {
            List<TourTeam> list = tourTeamRepo.findAllByTournament_id(tournament.get().getId());
            if (list.size() > 0) {
                return new TournamentTableDto(tournament.get(), list);
            }else {
                return new TournamentTableDto(tournament.get(), null);
            }

        } else {
            return new TournamentTableDto(null, null);
        }

    }

    public TourTeam setPoints(TourRequest tourRequest) {
        System.out.println(tourRequest);
        TourTeam tourTeam = tourTeamRepo.getTourTeamByTourNameAndTeamName(getDateNow(), tourRequest.getTeamName(), tourRequest.getTourName());
        tourTeam.setPoints(tourRequest.getTeamPoints());
        tourTeamRepo.save(tourTeam);

        return tourTeam;
    }

    public Optional<Tournament> getCurrentTournamentForTeamRegistr() {
        Optional<Tournament> tournament = tournamentRepo.findTournamentByDateNowForRegistr(getDateNow());

        return tournament;
    }


    private static String getDateNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());

        return date;
    }
}
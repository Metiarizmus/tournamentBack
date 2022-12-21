package com.scorina.competition.controllers.main;

import com.scorina.competition.controllers.main.payload.TourRequest;
import com.scorina.competition.controllers.main.payload.TournamentRequest;
import com.scorina.competition.dtos.TournamentTableDto;
import com.scorina.competition.models.Tournament;
import com.scorina.competition.payload.ResponseModel;
import com.scorina.competition.services.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final TournamentService tournamentService;

    @PostMapping("/tournament")
    public ResponseEntity<? extends ResponseModel<?>> createTournament(@RequestBody @Valid TournamentRequest tournamentRequest) {
        System.out.println(tournamentRequest);
        Tournament tournament = tournamentService.createTournament(tournamentRequest);
        if (tournament != null) {
            ResponseModel<Tournament> response = new ResponseModel<>();
            response.setData(tournament);
            response.setMessage("Tournament was successfully created!");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(new ResponseModel<>(null, "Sorry but the tournament wasn't created"));
        }
    }

    @PostMapping("/tour")
    public ResponseEntity<? extends ResponseModel<?>> setTourPoints(@RequestBody @Valid TourRequest tourPoints) {
        tournamentService.setPoints(tourPoints);
        return ResponseEntity.ok(new ResponseModel<>(tournamentService.getCurrentTournament(), "Points was successfully added."));
    }

    @GetMapping("/tournament")
    public ResponseEntity<? extends ResponseModel<?>> getCurrentTournament() {
        TournamentTableDto tableDto = tournamentService.getCurrentTournament();
        if (tableDto.getTournament() != null) {
            return ResponseEntity.ok(new ResponseModel<>(tableDto, ""));
        }else {
            return ResponseEntity.ok(new ResponseModel<>(tableDto, "You dont have tournaments"));
        }
    }
}

package com.scorina.competition.controllers.auth;

import com.scorina.competition.controllers.auth.payload.TeamRequest;
import com.scorina.competition.models.Tournament;
import com.scorina.competition.payload.ResponseModel;
import com.scorina.competition.services.TeamService;
import com.scorina.competition.services.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class TeamController {
    private final TeamService teamService;
    private final TournamentService tournamentService;


    @PostMapping("/team")
    public ResponseEntity<ResponseModel<Tournament>> registrationTeam(@RequestBody @Valid TeamRequest teamRequest) {
        System.out.println(teamRequest);
        boolean isRegistered = teamService.saveTeam(teamRequest);
        if (isRegistered) {
            return ResponseEntity.ok(new ResponseModel<>(null, "You was successfully registered."));
        }else {
            return ResponseEntity.ok(new ResponseModel<>(null, "Smth went wrong."));
        }
    }

    @GetMapping("/tournament")
    public ResponseEntity<ResponseModel<Tournament>> getInfoByCurrentTournament() {
        Optional<Tournament> tournament = tournamentService.getCurrentTournamentForTeamRegistr();
        return tournament.map(value -> ResponseEntity.ok(new ResponseModel<>(value, ""))).orElseGet(() -> ResponseEntity.ok(new ResponseModel<>(null, "We dont have some tournaments")));
    }
}

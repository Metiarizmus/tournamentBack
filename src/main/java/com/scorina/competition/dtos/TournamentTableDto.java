package com.scorina.competition.dtos;

import com.scorina.competition.models.TourTeam;
import com.scorina.competition.models.Tournament;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentTableDto {
    private Tournament tournament;
    private List<TourTeam> tourTeams;
}
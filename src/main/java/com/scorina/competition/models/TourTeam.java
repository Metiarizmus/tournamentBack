package com.scorina.competition.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "tour_team")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TourTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer points;

    @ManyToOne()
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @ManyToOne()
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

}

package com.scorina.competition.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Table(name = "tour")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private LocalDate date;

    @NotBlank
    private String tourName;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Set<TourTeam> tourTeams;

    @ManyToOne()
    @JoinColumn(name = "tournament_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Tournament tournament;
}

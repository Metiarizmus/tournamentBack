package com.scorina.competition.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Table(name = "tournament")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private LocalDate dateRegistrationFrom;

    private LocalDate dateRegistrationTo;

    @NotNull
    private Integer countTour;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Set<Team> teams;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Set<Tour> tours;

    public Tournament(LocalDate dateFrom, LocalDate dateTo, LocalDate dateRegistrationFrom,
                      LocalDate dateRegistrationTo, Integer countTour) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dateRegistrationFrom = dateRegistrationFrom;
        this.dateRegistrationTo = dateRegistrationTo;
        this.countTour = countTour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tournament that = (Tournament) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

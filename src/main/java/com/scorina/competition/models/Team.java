package com.scorina.competition.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Table(name = "team")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String teamName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String groupName;

    @NotNull
    private Integer countMembers;

    private Date dateRegistration;

    @ManyToOne()
    @JoinColumn(name = "tournament_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Tournament tournament;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Set<TourTeam> tourTeams;


    public Team(String teamName, String groupName, Integer countMembers) {
        this.teamName = teamName;
        this.groupName = groupName;
        this.countMembers = countMembers;
        this.setCurrentDate();
    }

    public Team() {
        this.setCurrentDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Team team = (Team) o;
        return id != null && Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    private void setCurrentDate() {
        dateRegistration = Calendar.getInstance().getTime();
    }
}

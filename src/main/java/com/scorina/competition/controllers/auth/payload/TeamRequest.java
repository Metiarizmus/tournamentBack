package com.scorina.competition.controllers.auth.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TeamRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String teamName;
    @NotBlank
    private String groupName;
    @NotNull
    private Integer countMembers;
}

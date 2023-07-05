package com.member.main.model;

import java.util.List;



import com.member.main.entity.TeamMember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequiredResponse {


    private TeamMember teamMember;
    private List<TeamLeader> TeamLeader;
}



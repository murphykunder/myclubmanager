package com.eapp.myclubmanager.team;

import org.springframework.stereotype.Service;

@Service
public class TeamMapper {
    public Team toTeam(TeamRequest request) {
        return new Team.Builder()
                .setId(request.id())
                .setName(request.name())
                .createTeam();
    }

    public TeamResponse toTeamResponse(Team team){
        return new TeamResponse.Builder()
                .setId(team.getId())
                .setName(team.getName())
                .createTeamResponse();
    }
}

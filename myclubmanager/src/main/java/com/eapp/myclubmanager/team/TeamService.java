package com.eapp.myclubmanager.team;

import com.eapp.myclubmanager.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

    public TeamService(TeamMapper teamMapper, TeamRepository teamRepository) {
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
    }

    public void save(TeamRequest request) {
        Team team = teamMapper.toTeam(request);
        teamRepository.save(team);
    }

    public PageResponse<TeamResponse> findAllTeams(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Team> teams = teamRepository.findAll(pageable);
        List<TeamResponse> teamResponses = teams.stream().map(teamMapper::toTeamResponse).toList();
        return new PageResponse<TeamResponse>(
                teamResponses,
                teams.getNumber(),
                teams.getSize(),
                teams.getTotalElements(),
                teams.getTotalPages(),
                teams.isFirst(),
                teams.isLast()
        );
    }
}

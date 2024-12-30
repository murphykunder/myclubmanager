package com.eapp.myclubmanager.team;

import com.eapp.myclubmanager.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("team")
@Tag(name="Team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/status")
    public ResponseEntity<String> status(){
        return ResponseEntity.ok("Team controller is working");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid TeamRequest request){
        teamService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<TeamResponse>> findAllTeams(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "25", required = false) int size
    ){
        return ResponseEntity.ok(teamService.findAllTeams(page, size));
    }
}

package com.eapp.myclubmanager.team;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("team")
@Tag(name="Team")
public class TeamController {

    @GetMapping("/status")
    public ResponseEntity<String> status(){
        return ResponseEntity.ok("Team controller is working");
    }

//    @PostMapping("/save")
//    public ResponseEntity<?> createTeam(@RequestBody @Valid TeamRequest request){
//        return ResponseEntity.ok(teamService.save(request));
//    }
}

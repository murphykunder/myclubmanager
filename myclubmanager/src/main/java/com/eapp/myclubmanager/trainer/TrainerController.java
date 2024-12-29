package com.eapp.myclubmanager.trainer;

import com.eapp.myclubmanager.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trainer")
@Tag(name="Trainer")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/status")
    public ResponseEntity<String> status(){
        return ResponseEntity.ok("Trainer module is running");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid TrainerRequest request){
        trainerService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<TrainerResponse>> findAllTrainers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "25", required = false) int size
    ){
        return ResponseEntity.ok(trainerService.findAllTrainers(page, size));
    }
}

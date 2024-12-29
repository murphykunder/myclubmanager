package com.eapp.myclubmanager.swimmer;

import com.eapp.myclubmanager.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("swimmer")
@Tag(name="Swimmer")
public class SwimmerController {

    private final SwimmerService swimmerService;

    public SwimmerController(SwimmerService swimmerService) {
        this.swimmerService = swimmerService;
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Swimmer module running");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid SwimmerRequest request) {
        swimmerService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<SwimmerResponse>> findAllSwimmers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "25", required = false) int size
    ) {
        return ResponseEntity.ok(swimmerService.findAllSwimmers(page,size));
    }


}

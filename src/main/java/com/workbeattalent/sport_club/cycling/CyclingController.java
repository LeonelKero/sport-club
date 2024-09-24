package com.workbeattalent.sport_club.cycling;

import com.workbeattalent.sport_club.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"${api.v1.prefix}/cycling"})
public record CyclingController(CyclingService cyclingService) {

    @GetMapping
    public ResponseEntity<ApiResponse> allTours() {
        return ResponseEntity.ok(new ApiResponse("All Cycling Tours", this.cyclingService.findAll()));
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ApiResponse> getOne(final @PathVariable Long id) {
        try {
            final var tour = this.cyclingService.getById(id);
            return ResponseEntity.ok(new ApiResponse("Cycling tour ID " + id, tour));
        } catch (CyclingNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Tour with ID " + id + " not found", null));
        }
    }

    @GetMapping(path = {"/location/{location}"})
    public ResponseEntity<ApiResponse> getByLocation(final @PathVariable String location) {
        return ResponseEntity.ok(new ApiResponse("Cycling Tours made " + location, this.cyclingService.findByLocation(location)));
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<ApiResponse> delete(final @PathVariable Long id) {
        try {
            this.cyclingService.delete(id);
            return ResponseEntity.ok(new ApiResponse("Tour " + id + " Successfully removed", null));
        } catch (CyclingNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getLocalizedMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> record(final @RequestBody CreateCyclingTour newTour) {
        try {
            this.cyclingService.save(newTour);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Cycling Tour recorded successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getLocalizedMessage(), null));
        }
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<ApiResponse> update(final @PathVariable Long id, final @RequestBody CreateCyclingTour cyclingTour) {
        try {
            this.cyclingService.update(id, cyclingTour);
            return ResponseEntity.ok(new ApiResponse("Cycling Tour updated successfully", null));
        } catch (CyclingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getLocalizedMessage(), null));
        }
    }

}

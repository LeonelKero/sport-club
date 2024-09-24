package com.workbeattalent.sport_club.cycling;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateCyclingTour(
        @NotEmpty
        String title,
        LocalDateTime startAt,
        LocalDateTime completedAt,
        @Positive
        Double distance,
        Location location
) {
    public CreateCyclingTour {
        if (this.startAt().isAfter(this.completedAt()))
            throw new IllegalArgumentException("Cannot complete a tour before it has started ");
    }
}

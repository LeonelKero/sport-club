package com.workbeattalent.sport_club.cycling;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record CreateCyclingTour(
        @NotEmpty
        String title,

        @DateTimeFormat
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime startedAt,

        @DateTimeFormat
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime completedAt,

        @Positive
        Double distance,

        Location location
) {
//        public CreateCyclingTour {
//                if (this.startedAt().isAfter(this.completedAt()))
//                        throw new IllegalArgumentException("Cannot complete a tour before it has started ");
//        }
}

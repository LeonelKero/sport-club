package com.workbeattalent.sport_club.cycling;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cycling") // Because Data JDBC assumes the table name is 'cycling_tour' as class name
public class CyclingTour {
    @Id
    private Long id;
    private String title;
    private LocalDateTime startedOn;
    private LocalDateTime completedOn;
    private Double distance;
    private Location location;
    @Version
    private Integer version;

    public CyclingTour(String title, LocalDateTime startedOn, LocalDateTime completedOn, Double distance, Location location) {
        this.title = title;
        this.startedOn = startedOn;
        this.completedOn = completedOn;
        this.distance = distance;
        this.location = location;
    }

    public CyclingTour(String title, LocalDateTime startedOn, LocalDateTime completedOn, Double distance, Location location, Integer version) {
        this.title = title;
        this.startedOn = startedOn;
        this.completedOn = completedOn;
        this.distance = distance;
        this.location = location;
        this.version = version;
    }
}

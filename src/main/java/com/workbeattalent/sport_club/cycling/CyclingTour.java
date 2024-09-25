package com.workbeattalent.sport_club.cycling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
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
}

package com.workbeattalent.sport_club.cycling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;

public class CyclingJsonLoader implements CommandLineRunner {

    private final CyclingService cyclingService;
    private final ObjectMapper objectMapper;

    public CyclingJsonLoader(CyclingService cyclingService, ObjectMapper objectMapper) {
        this.cyclingService = cyclingService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        try (final var data = TypeReference.class.getResourceAsStream("/data/cycling_tours.json")) {
            final var cyclingTours = this.objectMapper.readValue(data, CyclingTourList.class);
            this.cyclingService.saveAll(cyclingTours.tours());
        } catch (IOException e) {
            throw new RuntimeException("Unable to read the data file");
        }
    }
}

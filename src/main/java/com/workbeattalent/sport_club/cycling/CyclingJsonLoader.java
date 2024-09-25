package com.workbeattalent.sport_club.cycling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CyclingJsonLoader implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(CyclingJsonLoader.class);

    private final CyclingService cyclingService;
    private final ObjectMapper objectMapper;

    public CyclingJsonLoader(CyclingService cyclingService, ObjectMapper objectMapper) {
        this.cyclingService = cyclingService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        if (this.cyclingService.count() == 0) {
            try (final var data = TypeReference.class.getResourceAsStream("/data/cycling_tours.json")) {
                final var cyclingTours = this.objectMapper.readValue(data, CyclingTourList.class);
                this.cyclingService.saveAll(cyclingTours.tours());
            } catch (IOException e) {
                throw new RuntimeException("Unable to read the data file");
            }
        } else {
            LOG.info("Not loading data from JSON file because the collection contains some data!");
        }
    }
}

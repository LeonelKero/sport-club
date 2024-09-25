package com.workbeattalent.sport_club.cycling;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DataJdbcCyclingRepository extends ListCrudRepository<CyclingTour, Long> {
    List<CyclingTour> findAllByLocation(final String location);
}

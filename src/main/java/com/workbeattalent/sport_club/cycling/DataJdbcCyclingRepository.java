package com.workbeattalent.sport_club.cycling;

import org.springframework.data.repository.ListCrudRepository;

public interface DataJdbcCyclingRepository extends ListCrudRepository<CyclingTour, Long> {
}

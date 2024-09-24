package com.workbeattalent.sport_club.cycling;

import java.util.Collection;

public interface CyclingService {
    Collection<CyclingTour> findAll();

    Collection<CyclingTour> findByLocation(final String location);

    void save(final CreateCyclingTour cyclingTour);

    void update(final Long tour, final CreateCyclingTour cyclingTour);

    void delete(final Long tour);

    CyclingTour getById(final Long tour);
}

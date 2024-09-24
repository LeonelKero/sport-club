package com.workbeattalent.sport_club.cycling;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CyclingTourServiceImpl implements CyclingService {

    private final CyclingRepository cyclingRepository;

    public CyclingTourServiceImpl(CyclingRepository cyclingRepository) {
        this.cyclingRepository = cyclingRepository;
    }

    @Override
    public Collection<CyclingTour> findAll() {
        return this.cyclingRepository.findAll();
    }

    @Override
    public Collection<CyclingTour> findByLocation(final String loc) {
        return this.cyclingRepository.findByLocation(Location.valueOf(loc));
    }

    @Override
    public void save(final CreateCyclingTour cyclingTour) {
        this.cyclingRepository.save(cyclingTour);
    }

    @Override
    public void update(final Long tour, final CreateCyclingTour cyclingTour) {
        try {
            this.cyclingRepository.update(tour, cyclingTour);
        } catch (Exception e) {
            throw new CyclingNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(final Long tour) {
        try {
            this.cyclingRepository.delete(tour);
        } catch (IllegalStateException e) {
            throw new CyclingNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public CyclingTour getById(final Long tour) {
        final var result = this.cyclingRepository.findById(tour);
        if (result.isEmpty()) throw new CyclingNotFoundException("Cycling Tour not found");
        return result.get();
    }
}

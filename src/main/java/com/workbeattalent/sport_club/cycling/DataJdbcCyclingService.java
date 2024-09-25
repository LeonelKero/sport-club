package com.workbeattalent.sport_club.cycling;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Primary
@Service(value = "data-jdbc")
public class DataJdbcCyclingService implements CyclingService {

    private final DataJdbcCyclingRepository cyclingRepository;

    public DataJdbcCyclingService(DataJdbcCyclingRepository cyclingRepository) {
        this.cyclingRepository = cyclingRepository;
    }

    @Override
    public Collection<CyclingTour> findAll() {
        return this.cyclingRepository.findAll();
    }

    @Override
    public Collection<CyclingTour> findByLocation(final String location) {
        return this.cyclingRepository.findAll()
                .stream()
                .filter(tour -> tour.getLocation().toString().equals(location.trim()))
                .toList();
    }

    @Override
    public void save(final CreateCyclingTour cyclingTour) {
        this.cyclingRepository.save(new CyclingTour(
                cyclingTour.title(),
                cyclingTour.startedAt(),
                cyclingTour.completedAt(),
                cyclingTour.distance(),
                cyclingTour.location()
        ));
    }

    @Override
    public void update(final Long tour, final CreateCyclingTour cyclingTour) {
        final var optionalTour = this.cyclingRepository.findById(tour);
        if (optionalTour.isEmpty()) throw new CyclingNotFoundException("Cycling Tour not found");
        final var existingTour = optionalTour.get();
        existingTour.setTitle(cyclingTour.title());
        existingTour.setStartedOn(cyclingTour.startedAt());
        existingTour.setCompletedOn(cyclingTour.completedAt());
        existingTour.setDistance(cyclingTour.distance());
        existingTour.setLocation(cyclingTour.location());

        this.cyclingRepository.save(existingTour);
    }

    @Override
    public void delete(final Long tour) {
        if (this.cyclingRepository.findById(tour).isEmpty())
            throw new CyclingNotFoundException("Cycling Tour not found");
        this.cyclingRepository.deleteById(tour);
    }

    @Override
    public CyclingTour getById(final Long tour) {
        return this.cyclingRepository.findById(tour)
                .orElseThrow(() -> new CyclingNotFoundException("Cycling Tour not found"));
    }

    @Override
    public void saveAll(final List<CreateCyclingTour> tours) {
        final var cyclingTours = tours.stream()
                .map(tour -> new CyclingTour(tour.title(), tour.startedAt(), tour.completedAt(), tour.distance(), tour.location()))
                .toList();
        this.cyclingRepository.saveAll(cyclingTours);
    }

    @Override
    public Integer count() {
        return Math.toIntExact(this.cyclingRepository.count());
    }
}

package com.workbeattalent.sport_club.cycling;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service(value = "jdbc-client")
public class JdbcClientCyclingService implements CyclingService {

    private final JdbcClientCyclingRepository JDBCClientCyclingRepository;

    public JdbcClientCyclingService(JdbcClientCyclingRepository JDBCClientCyclingRepository) {
        this.JDBCClientCyclingRepository = JDBCClientCyclingRepository;
    }

    @Override
    public Collection<CyclingTour> findAll() {
        return this.JDBCClientCyclingRepository.findAll();
    }

    @Override
    public Collection<CyclingTour> findByLocation(final String loc) {
        return this.JDBCClientCyclingRepository.findByLocation(Location.valueOf(loc));
    }

    @Override
    public void save(final CreateCyclingTour cyclingTour) {
        this.JDBCClientCyclingRepository.save(cyclingTour);
    }

    @Override
    public void update(final Long tour, final CreateCyclingTour cyclingTour) {
        try {
            this.JDBCClientCyclingRepository.update(tour, cyclingTour);
        } catch (Exception e) {
            throw new CyclingNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(final Long tour) {
        try {
            this.JDBCClientCyclingRepository.delete(tour);
        } catch (IllegalStateException e) {
            throw new CyclingNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public CyclingTour getById(final Long tour) {
        final var result = this.JDBCClientCyclingRepository.findById(tour);
        if (result.isEmpty()) throw new CyclingNotFoundException("Cycling Tour not found");
        return result.get();
    }

    @Override
    public void saveAll(final List<CreateCyclingTour> tours) {
        this.JDBCClientCyclingRepository.saveAll(tours);
    }

    @Override
    public Integer count() {
        return this.JDBCClientCyclingRepository.count();
    }
}

package com.workbeattalent.sport_club.cycling;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class JdbcClientCyclingRepository {

    private final JdbcClient jdbcClient;

    public JdbcClientCyclingRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Collection<CyclingTour> findAll() {
        return this.jdbcClient.sql("select * from cycling")
                .query(CyclingTour.class)
                .list();
    }

    public Optional<CyclingTour> findById(final Long tour) {
        return this.jdbcClient.sql("select * from cycling where id = :id")
                .param("id", tour)
                .query(CyclingTour.class)
                .optional();
    }

    public void save(final CreateCyclingTour tour) {
        final var affectedRows = this.jdbcClient
                .sql("insert into cycling(title, started_on, completed_on, distance, location) " +
                        "values (?, ?, ?, ?, ?)")
                .params(List.of(tour.title(), tour.startedAt(), tour.completedAt(),
                        tour.distance(), tour.location().toString()))
                .update();
        // If this assertion is not met, a runtime exception is thrown and stop
        Assert.state(affectedRows == 1, "Unable to create tour " + tour.title().toUpperCase(Locale.ROOT) + ". An error occurred");
    }

    public Collection<CyclingTour> findByLocation(final Location location) {
        return this.jdbcClient.sql("select * from cycling where location = :loc")
                .param("loc", location.toString())
                .query(CyclingTour.class)
                .list();
    }

    public void update(final Long tour, final CreateCyclingTour cyclingTour) {
        final var affectedRows = this.jdbcClient
                .sql("update cycling set title = ?, started_on = ?, completed_on = ?, " +
                        "distance = ?, location = ? where id = ?")
                .params(List.of(cyclingTour.title(), cyclingTour.startedAt(), cyclingTour.completedAt(),
                        cyclingTour.distance(), cyclingTour.location(), tour))
                .update();
        Assert.state(affectedRows == 1, "Unable to update tour " + tour);
    }

    public void delete(final Long id) {
        final var affectedRows = this.jdbcClient.sql("delete from cycling where id = :id")
                .param("id", id)
                .update();
        Assert.state(affectedRows == 1, "Unable to delete tour " + id + ", resource not found");
    }

    public Integer count() {
        return this.jdbcClient.sql("select * from cycling")
                .query()
                .listOfRows()
                .size();
    }

    public void saveAll(final Collection<CreateCyclingTour> cyclingTours) {
        cyclingTours.forEach(this::save);
    }
}

package com.workbeattalent.sport_club.cycling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import({DataJdbcCyclingService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DataJdbcCyclingServiceTest {

    @Autowired
    private DataJdbcCyclingService underTest;

    @BeforeEach
    void setUp() {
        final var tour1 = new CreateCyclingTour("Banengo", LocalDateTime.of(2024, 6, 12, 6, 30, 0),
                LocalDateTime.of(2024, 6, 12, 8, 30, 0), 5.5, Location.IN_CITY);
        this.underTest.save(tour1);
    }

    @Test
    void findAll() {
        // GIVEN // WHEN
        final var response = this.underTest.findAll();
        // THEN
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    void findByLocation() {
        // GIVEN

        // WHEN

        // THEN
    }

    @Test
    void save() {
        // GIVEN

        // WHEN

        // THEN
    }

    @Test
    void update() {
        // GIVEN

        // WHEN

        // THEN
    }

    @Test
    void delete() {
        // GIVEN

        // WHEN

        // THEN
    }

    @Test
    void getById() {
        // GIVEN

        // WHEN

        // THEN
    }

    @Test
    void saveAll() {
        // GIVEN

        // WHEN

        // THEN
    }

    @Test
    void count() {
        // GIVEN

        // WHEN

        // THEN
    }
}
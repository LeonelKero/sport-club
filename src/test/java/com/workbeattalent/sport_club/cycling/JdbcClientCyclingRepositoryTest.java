package com.workbeattalent.sport_club.cycling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@Import(value = JdbcClientCyclingRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcClientCyclingRepositoryTest {

    @Autowired
    private JdbcClientCyclingRepository underTest;

    @BeforeEach
    void setup() {
        final var tour1 = new CreateCyclingTour("Banengo", LocalDateTime.of(2024, 6, 12, 6, 30, 0),
                LocalDateTime.of(2024, 6, 12, 8, 30, 0), 5.5, Location.IN_CITY);

        final var tour2 = new CreateCyclingTour("Tamdja", LocalDateTime.of(2024, 6, 24, 6, 30, 0),
                LocalDateTime.of(2024, 6, 24, 8, 30, 0), 5.5, Location.INDOOR);

        final var tour3 = new CreateCyclingTour("Ndjiandam", LocalDateTime.of(2024, 8, 15, 6, 30, 0),
                LocalDateTime.of(2024, 8, 15, 8, 30, 0), 5.5, Location.IN_CITY);

        this.underTest.saveAll(List.of(tour1, tour2, tour3));
    }

    @Test
    void shouldGetAllCyclingTours() {
        final var response = this.underTest.findAll();
        assertThat(response.size()).isEqualTo(3);
    }

    @Test
    void shouldFindById() {
        final var response = this.underTest.findById(2L);
//        assertThat(response.isPresent()).isTrue();
        assertThat(response.get().getTitle()).isEqualTo("Tamdja");
    }

    @Test
    void shouldReturnNothingForNotExistingTour() {
        final var response = this.underTest.findById(5L);
        assertThat(response.isEmpty()).isTrue();
    }

    @Test
    void shouldAddNewTourToDatabase() {
        final var newTour = new CreateCyclingTour(
                "Marche A",
                LocalDateTime.of(2024, 2, 8, 5, 30, 0),
                LocalDateTime.of(2024, 2, 8, 7, 30, 0),
                10.,
                Location.IN_CITY);
        this.underTest.save(newTour);
        assertThat(this.underTest.count()).isEqualTo(4);
    }

    @Test
    void shouldFindByLocation() {
        final var response = this.underTest.findByLocation(Location.IN_CITY);
        assertThat(response.size()).isEqualTo(2);
    }

    @Test
    void whenLocationDoNotExistShouldReturnEmptyList() {
        final var response = this.underTest.findByLocation(Location.IN_TOWN);
        assertThat(response.size()).isEqualTo(0);
    }

    //    @Test
    void shouldUpdateTour() {
        // Given  // TODO: Miss behaving method, must be fixed
        final var updatedTitle = "Updated title";
        this.underTest.update(1L, new CreateCyclingTour(
                updatedTitle,
                LocalDateTime.of(2024, 2, 8, 5, 30, 0),
                LocalDateTime.of(2024, 2, 8, 7, 30, 0),
                10.,
                Location.IN_CITY));
        // When
        final var updatedTour = this.underTest.findById(1L).get();
        // Then
        assertThat(updatedTour.getTitle()).isEqualTo(updatedTitle);
    }

    @Test
    void shouldSaveAll() {
        final var tour1 = new CreateCyclingTour("Banengo", LocalDateTime.of(2024, 6, 12, 6, 30, 0),
                LocalDateTime.of(2024, 6, 12, 8, 30, 0), 5.5, Location.IN_CITY);

        final var tour2 = new CreateCyclingTour("Tamdja", LocalDateTime.of(2024, 6, 24, 6, 30, 0),
                LocalDateTime.of(2024, 6, 24, 8, 30, 0), 5.5, Location.INDOOR);

        final var tour3 = new CreateCyclingTour("Ndjiandam", LocalDateTime.of(2024, 8, 15, 6, 30, 0),
                LocalDateTime.of(2024, 8, 15, 8, 30, 0), 5.5, Location.IN_CITY);

        this.underTest.saveAll(List.of(tour1, tour2, tour3));
        assertThat(this.underTest.count()).isEqualTo(6);
    }

    @Test
    void shouldDeleteTourById() {
        this.underTest.delete(1L);
        assertThat(this.underTest.count()).isEqualTo(2);
    }

    @Test
    void whenDeletingNotExistingTourThrowException() {
        final var fakeID = 40L;
        assertThatThrownBy(() -> this.underTest.delete(fakeID))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Unable to delete tour 40, resource not found");
    }

    @Test
    void shouldCountAllTours() {
        final var response = this.underTest.count();
        assertThat(response).isEqualTo(3);
    }
}
package com.workbeattalent.sport_club.cycling;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest({CyclingController.class})
class CyclingControllerTest {

    private static final String BASE_URL = "/api/v1/sports";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CyclingService cyclingService;

    private final List<CyclingTour> tours = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tours.add(new CyclingTour(
                1L,
                "Banengo",
                LocalDateTime.of(2024, 6, 12, 6, 30, 0),
                LocalDateTime.of(2024, 6, 12, 8, 30, 0),
                5.5,
                Location.IN_CITY,
                1));
    }

    @Test
    void shouldFindAllTours() throws Exception {
        // GIVEN // WHEN
        when(this.cyclingService.findAll()).thenReturn(tours);
        // THEN
        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/cycling"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // the '$' symbol represent the returned object (in this case the ApiResponse which has two parts {message} and {data})
                .andExpect(MockMvcResultMatchers.jsonPath(
                                "$.data.size()",
                                Matchers.is(tours.size())
                        )
                );
    }

    @Test
    void shouldReturnNotForNotExistingTourById() throws Exception {
        // GIVEN // WHEN
        when(this.cyclingService.getById(99L))
                .thenThrow(CyclingNotFoundException.class);
        // THEN
        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/cycling/99"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Tour with ID 99 not found")));
    }

    @Test
    void shouldReturnTourById() throws Exception {
        // GIVEN
        final var tour = tours.get(0);
        // WHEN
        when(this.cyclingService.getById(1L)).thenReturn(tour);
        // THEN
        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/cycling/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Cycling tour ID 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title", Matchers.is(tour.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.distance", Matchers.is(tour.getDistance())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.location", Matchers.is(tour.getLocation().toString())));
    }

    @Test
    void shouldReturnToursByLocation() throws Exception {
        // GIVEN // WHEN
        when(this.cyclingService.findByLocation("IN_CITY")).thenReturn(tours);
        // THEN
        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/cycling/sort?location=in_city"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()", Matchers.is(1)));
    }

    @Test
    void shouldAddTour() throws Exception {
        // GIVEN
        final var tour = new CreateCyclingTour(
                "Banengo",
                LocalDateTime.of(2024, 6, 12, 6, 30, 0),
                LocalDateTime.of(2024, 6, 12, 8, 30, 0),
                5.5,
                Location.IN_CITY);

        // WHENc // THEN
        mvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/cycling")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tour)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(this.cyclingService, times(1))
                .save(any(CreateCyclingTour.class));
    }

    // TODO: Update method follows pretty much the same approach
}
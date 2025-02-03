package dev.Fabricio.runnerz.run;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RunController.class)

public class RunControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    RunRepository runRepository;

    private final List<Run> runs = new ArrayList<>();
    @BeforeEach
    void setUp(){
        runs.add(new Run(1,
            "Morning Run",
            LocalDateTime.of(2021, 1, 1, 6, 0),
            LocalDateTime.of(2021, 1, 1, 7, 0),
            5,
            Location.INDOOR,
            null
        ));
    }


    @Test
    void testCreate() {
        when(runRepository.create(any(Run.class))).thenReturn(new Run(1,
            "Morning Run",
            LocalDateTime.of(2021, 1, 1, 6, 0),
            LocalDateTime.of(2021, 1, 1, 7, 0),
            5,
            Location.INDOOR,
            null
        ));
        mvc.perform(post("/runs"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(new Run(
                1,
                "Morning Run",
                LocalDateTime.of(2021, 1, 1, 6, 0),
                LocalDateTime.of(2021, 1, 1, 7, 0),
                5,
                Location.INDOOR,
                null
            )))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(new Run(1,
                "Morning Run",
                LocalDateTime.of(2021, 1, 1, 6, 0),
                LocalDateTime.of(2021, 1, 1, 7, 0),
                5,
                Location.INDOOR,
                null
            ))));
    }

    @Test
    void testDelete() {
        when(runRepository.delete(Run.class)).thenReturn(true);
        mvc.perform(delete("api/runs/1"))
            .andExpect(status().isOk());
    }

    @Test
    void testGetRun() throws Exception{
        Run run = runs.get(0);
        when(runRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(run));
        mvc.perform(MockMvcRequestBuilders.get("/api/runs/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(run.id())))
            .andExpect(jsonPath("$.title", is(run.title())));
    }

    @Test
    void testGetRuns() throws Exception{
        when(runRepository.findAll()).thenReturn(runs);
        mvc.perform(MockMvcRequestBuilders.get("/api/runs"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(runs.size())));
    }

    @Test
    void testGetRunsByLocation() {
        when(runRepository.findByLocation(Location.INDOOR)).thenReturn(runs);
        mvc.perform(get("/runs?location=INDOOR"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(runs)));
    }

    @Test
    void testUpdate() {
        when(runRepository.update(any(Run.class))).thenReturn(new Run(1,
            "Morning Run",
            LocalDateTime.of(2021, 1, 1, 6, 0),
            LocalDateTime.of(2021, 1, 1, 7, 0),
            5,
            Location.INDOOR,
            null
        ));
    }
}

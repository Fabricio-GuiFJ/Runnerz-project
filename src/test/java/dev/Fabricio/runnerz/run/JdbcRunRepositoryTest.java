package dev.Fabricio.runnerz.run;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;


@JdbcTest
@Import(JdbcRunRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JdbcRunRepositoryTest {

    @Autowired
    JdbcRunRepository repository;


    @BeforeEach
    void setUp() {
         repository.create(new Run(
            1,
            "Morning Run",
            LocalDateTime.of(2021, 1, 1, 6, 0),
            LocalDateTime.of(2021, 1, 1, 7, 0),
            5,
            Location.INDOOR,
            null
         ));

            repository.create(new Run(
                2,
                "Evening Run",
                LocalDateTime.of(2021, 1, 1, 18, 0),
                LocalDateTime.of(2021, 1, 1, 19, 0),
                5,
                Location.OUTDOOR,
                null
            ));
    }

    @Test
    void testCount() {
        assertEquals(2, repository.count(),"Should have 2 runs");
    }

    @Test
    void testCreate() {
        repository.create(new Run(
            3,
            "Afternoon Run",
            LocalDateTime.of(2021, 1, 1, 12, 0),
            LocalDateTime.of(2021, 1, 1, 13, 0),
            5,
            Location.OUTDOOR,
            null
        ));

        assertEquals(3, repository.count(),"Should have 3 runs");
    }

    @Test
    void testDelete() {
        repository.delete(3);
        assertEquals(2, repository.count(),"Should have 2 runs");
    }

    @Test
    void testFindAll() {
        assertEquals(2, repository.findAll().size(),"Should have 2 runs");
    }

    @Test
    void testFindById() {
        Run run = repository.findById(1).get();
        assertEquals("Morning Run", run.title(),"Should have Morning Run");
    }

    @Test
    void testFindByLocation() {
        assertEquals(1, repository.findByLocation(Location.INDOOR).size(),"Should have 1 run");
    }

    @Test
    void testUpdate() {
        Run run = repository.findById(1).get();
        run = new Run(
            run.id(),
            "Morning Run",
            LocalDateTime.of(2021, 1, 1, 6, 0),
            LocalDateTime.of(2021, 1, 1, 7, 0),
            10,
            Location.INDOOR,
            null
        );
        repository.update(run, 1);
        run = repository.findById(1).get();
        assertEquals(10, run.miles(),"Should have 10 miles");
    }
}

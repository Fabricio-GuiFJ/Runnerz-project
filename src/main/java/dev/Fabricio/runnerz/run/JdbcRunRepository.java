package dev.Fabricio.runnerz.run;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRunRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcRunRepository.class.getName()); 
    private final JdbcClient jdbcClient;

    public JdbcRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM Run")
            .query(Run.class).list()
        ;
    }

    public void create(Run run) {
        jdbcClient.sql("INSERT INTO Run(ID,TITLE,STARTED_ON,COMPLETED_ON,MILES,LOCATION)VALUES (?, ?, ?, ?, ?, ?)")
        .params(List.of(
            run.id(), 
            run.title(), 
            run.startedOn(), 
            run.completedOn(), 
            run.miles(), 
            run.location().toString()))
            .update()
            
        ;
        log.info("Run created: {}", run);
    }

    public void delete(Integer id) {
        jdbcClient.sql("DELETE FROM Run WHERE ID = :id")
            .param("id", id)
            .update()
        ;
        log.info("Run deleted: {}", id);
    }

    public void update(Run run, Integer id) {
        jdbcClient.sql("UPDATE Run SET TITLE = ?, STARTED_ON = ?, COMPLETED_ON = ?, MILES = ?, LOCATION = ? WHERE ID = ?")
            .params(List.of(
                run.title(),
                run.startedOn(),
                run.completedOn(),
                run.miles(),
                run.location().toString(),
                run.id()
            ))
            .update()
        ;
        log.info("Run updated: {}", run);
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM Run WHERE ID = :id")
            .param("id", id)
            .query(Run.class).optional()
        ;
    }

    public int count() {
        return jdbcClient.sql("select * from Run")
            .query().listOfRows().size()
        ;
    }

    public void saveAll(List<Run> runs) {
        runs.forEach(this::create);
    }

    public List<Run> findByLocation(Location location) {
        return jdbcClient.sql("SELECT * FROM Run WHERE LOCATION = :location")
            .param("location", location.toString())
            .query(Run.class).list()
        ;
    }
}

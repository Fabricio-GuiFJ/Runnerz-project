package dev.Fabricio.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll(){
        return runs;
    }

    Optional<Run> findById(Integer id){
        return runs.stream().filter(run -> run.id().equals(id)).findFirst();
    }


    
    @PostConstruct
    public void init(){
        Integer id = 1;
        String title = "Morning Run";
        LocalDateTime startedOn = LocalDateTime.now().minusHours(1);
        LocalDateTime finishedOn = LocalDateTime.now();
        Integer miles = 5;

        Run run = new Run(id, title, startedOn, finishedOn, miles,Location.OUTDOOR);
        runs.add(run);

        runs.add(new Run(2, "Evening Run", LocalDateTime.now().minusHours(2), LocalDateTime.now(), 10, Location.INDOOR));
    }

    void create(Run run){
        runs.add(run);
    }
}

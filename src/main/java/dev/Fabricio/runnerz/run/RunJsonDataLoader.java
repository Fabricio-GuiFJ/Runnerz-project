package dev.Fabricio.runnerz.run;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class.getName());
    private final JdbcRunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(JdbcRunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/Data/runs.json")){
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON file", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            } catch (IOException e){
                log.error("Error loading data", e);
            }
        }else{
            log.info("Not loading From JSON file, data already loaded {} runs", runRepository.count());
        }
    }
    

}

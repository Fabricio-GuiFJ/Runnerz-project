package dev.Fabricio.runnerz.run;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.jdbc.repository.query.Query;

public interface RunRepository extends ListCrudRepository<Run, Integer> {

    @Query
    Runs findAllByLocation(String location);
}

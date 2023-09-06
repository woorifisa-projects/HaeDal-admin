package dev.syntax.backend.log.repository;


import dev.syntax.backend.log.model.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
    Log save(Log log);

    List<Log> findAll();
}

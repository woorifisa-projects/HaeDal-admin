package dev.syntax.backend.log.service;

import dev.syntax.backend.log.model.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {
    Log save(Log log);

    List<Log> findAll();
}

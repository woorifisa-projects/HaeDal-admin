package dev.syntax.backend.log.service;

import dev.syntax.backend.log.model.Log;
import dev.syntax.backend.log.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImp implements LogService{
    private LogRepository logRepository;

    public LogServiceImp(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }
}

package dev.syntax.backend.log.controller;

import dev.syntax.backend.log.dto.response.LogResponse;
import dev.syntax.backend.log.model.Log;
import dev.syntax.backend.log.service.LogService;
import dev.syntax.backend.product.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin/log")
public class LogController {

    private LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<LogResponse> allLogShow() {
        List<Log> logs = logService.findAll();
        System.out.println(logs);
        List<LogResponse> logResponse = logs.stream()
                .map(LogResponse::from)
                .collect(Collectors.toList());
        return logResponse;
    }

}

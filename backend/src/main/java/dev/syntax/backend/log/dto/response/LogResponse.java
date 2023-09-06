package dev.syntax.backend.log.dto.response;

import dev.syntax.backend.log.model.Log;
import dev.syntax.backend.log.model.LogType;
import dev.syntax.backend.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LogResponse {
    private final long logId;
    private final long userId;
    private final  LogType logType;
    private final LocalDateTime logTime;
    private final String logDesc;

    @Builder
    public LogResponse(long logId, long userId, LogType logType, LocalDateTime logTime, String logDesc) {
        this.logId = logId;
        this.userId = userId;
        this.logType = logType;
        this.logTime = logTime;
        this.logDesc = logDesc;
    }

    public static LogResponse from(Log log){
        return LogResponse.builder()
                .logId(log.getLogId())
                .userId(log.getUser().getUserId())
                .logType(log.getLogType())
                .logTime(log.getLogTime())
                .logDesc(log.getLogDesc())
                .build();
    }
}

package backend.security.dashboard.mapper;

import backend.security.dashboard.dto.LogDTO;
import backend.security.dashboard.model.Log;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {

    LogDTO toLogDTO(Log log);

    List<LogDTO> toLogsDTO(List<Log> logs);
}

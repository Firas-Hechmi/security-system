package backend.security.dashboard.controller;

import backend.security.dashboard.dto.FilterLogsDTO;
import backend.security.dashboard.dto.LogDTO;
import backend.security.dashboard.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/logs")
public class LogController {

    private LogService logService;

    @Autowired
    public LogController(LogService logService){
        this.logService = logService;
    }

    @GetMapping
    ResponseEntity<List<LogDTO>> findAllLogs(){
        return ResponseEntity.ok().body(logService.getAllLogs());
    }

    @PostMapping("/filter")
    ResponseEntity<List<LogDTO>> filterLogs(@RequestBody FilterLogsDTO filterLogs){
        return ResponseEntity.ok().body(logService.filterLogs(filterLogs));
    }
}

package backend.security.dashboard.controller;

import backend.security.dashboard.dto.RequestDTO;
import backend.security.dashboard.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService){
        this.requestService = requestService;
    }

    @GetMapping
    public ResponseEntity<List<RequestDTO>> getAllRequests(){
          return ResponseEntity.ok().body(requestService.getAllRequest());
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id){
          this.requestService.deleteRequest(id);
    }


}

package backend.security.dashboard.service;

import backend.security.dashboard.dto.RequestDTO;
import backend.security.dashboard.mapper.RequestMapper;
import backend.security.dashboard.model.Request;
import backend.security.dashboard.repository.RequestRepository;
import backend.security.dashboard.security.CachedBodyHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    private final RequestMapper requestMapper;

    private  final Logger logger = LoggerFactory.getLogger(RequestService.class);

    @Autowired
    public RequestService(RequestRepository requestRepository,RequestMapper requestMapper){
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    public List<RequestDTO> getAllRequest(){
        return requestMapper.toRequestListDTO(requestRepository.findAllRequests());
    }

    public void addRequest(CachedBodyHttpServletRequest request, HttpServletResponse response){
        try {
                String requestBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
                URL url = new URL(request.getRequestURL().toString());
                Request req = new Request(LocalDateTime.now(), request.getMethod(), url.toString(), request.getRemoteAddr(), requestBody, response.getStatus());
                requestRepository.save(req);
        }catch (Exception e){
            //
        }
    }

    public void deleteRequest(Long id){
        try{
            Optional<Request> requestOptional = id != null ? this.requestRepository.findById(id) : Optional.empty();
            if(requestOptional.isPresent()){
                 Request request = requestOptional.get();
                 requestRepository.delete(request);
            }
        }catch (Exception exception){
               logger.error("Error Deleting request with id {} : {] ",id, exception.getMessage());
        }
    }
}

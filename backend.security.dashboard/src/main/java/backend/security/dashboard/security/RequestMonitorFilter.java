package backend.security.dashboard.security;

import backend.security.dashboard.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestMonitorFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestMonitorFilter.class);

    @Autowired
    private RequestService requestService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        CachedBodyHttpServletRequest cachedBodyHttpServletRequest =
                new CachedBodyHttpServletRequest(request);
        try {
            filterChain.doFilter(cachedBodyHttpServletRequest, response);
        } finally {
            requestService.addRequest(cachedBodyHttpServletRequest,response);
        }
    }

}
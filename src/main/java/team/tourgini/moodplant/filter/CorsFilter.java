package team.tourgini.moodplant.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.*;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private final List<String> ALLOW_ORIGINS = List.of(
            "http://localhost:3000"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Set the allowed headers
        String origin = httpRequest.getHeader(ORIGIN);
        if (Objects.isNull(origin)) {
            chain.doFilter(request, response);
            return;
        }

        if (ALLOW_ORIGINS.contains(origin)) {
            httpResponse.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            httpResponse.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            httpResponse.setHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS");
            httpResponse.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
            httpResponse.setHeader(ACCESS_CONTROL_EXPOSE_HEADERS, LOCATION);
        }

        // Handle preflight OPTIONS requests
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

}

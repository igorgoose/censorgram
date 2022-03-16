package by.schepov.bsu.diploma.censorgram.main.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infin.it.ufinance.platform.core.model.pojo.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handler class for logout success
 * Created by dkadirbekov on 26.07.2018.
 */
@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private ObjectMapper objectMapper;

    @Autowired
    public CustomLogoutSuccessHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(objectMapper.writeValueAsString(AuthenticationResponse.builder().build()));
        response.getWriter().flush();
        response.getWriter().close();
    }
}

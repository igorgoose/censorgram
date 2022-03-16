package by.schepov.bsu.diploma.censorgram.main.security.handler;

import by.schepov.bsu.diploma.censorgram.main.security.model.AuthenticationResponse;
import by.schepov.bsu.diploma.censorgram.main.util.MessageCodes;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final MessageSource messageSource;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String message = BadCredentialsException.class.isAssignableFrom(exception.getClass()) ?
                         messageSource.getMessage(MessageCodes.BAD_CREDENTIALS, null, request.getLocale()) :
                         exception.getMessage();
        response.getWriter().write(objectMapper.writeValueAsString(new AuthenticationResponse().setMessage(message)));
        response.getWriter().flush();
        response.getWriter().close();
    }
}

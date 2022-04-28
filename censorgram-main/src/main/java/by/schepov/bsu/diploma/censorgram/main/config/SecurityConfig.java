package by.schepov.bsu.diploma.censorgram.main.config;

import by.schepov.bsu.diploma.censorgram.main.repository.UserRepository;
import by.schepov.bsu.diploma.censorgram.main.security.handler.CustomAuthenticationFailureHandler;
import by.schepov.bsu.diploma.censorgram.main.security.handler.CustomAuthenticationSuccessHandler;
import by.schepov.bsu.diploma.censorgram.main.security.handler.CustomLogoutSuccessHandler;
import by.schepov.bsu.diploma.censorgram.main.service.UserService;
import by.schepov.bsu.diploma.censorgram.main.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

import static by.schepov.bsu.diploma.censorgram.main.model.entity.Authority.Code.POSTS_EDIT;
import static by.schepov.bsu.diploma.censorgram.main.model.entity.Authority.Code.POSTS_VIEW;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/", "/**").not().anonymous()
                .antMatchers("/api/v1/posts/**", "/api/v1/posts").hasAnyAuthority(POSTS_VIEW.name(), POSTS_EDIT.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .failureHandler(customAuthenticationFailureHandler)
                .successHandler(customAuthenticationSuccessHandler)
                .and()
                .logout().logoutUrl("/api/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .sessionManagement()
                .sessionAuthenticationFailureHandler(customAuthenticationFailureHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, passwordEncoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return userService();
    }
}

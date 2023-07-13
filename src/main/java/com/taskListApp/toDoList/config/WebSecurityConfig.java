package com.taskListApp.toDoList.config;

import com.taskListApp.toDoList.AuthorizedUser;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

import static com.taskListApp.toDoList.model.Role.ADMIN;
import static com.taskListApp.toDoList.model.Role.USER;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
@Setter
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;

   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
       return new BCryptPasswordEncoder();
   }




    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            log.debug("Authenticating '{}'", email);
            Optional<User> optionalUser = userRepository.findByEmail(email);
            return new AuthorizedUser(optionalUser.orElseThrow(
                    () -> new UsernameNotFoundException("User '" + email + "' was not found")));
        };
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/rest/profile").anonymous()
                .antMatchers("/rest/**").authenticated()
                .antMatchers("/rest/admin/**").hasRole(ADMIN.name())
                .antMatchers("/rest/profile/**").hasRole(USER.name())
                .and().formLogin()
                .defaultSuccessUrl("/rest/profile")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/rest/profile")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().csrf().disable();
    }
}


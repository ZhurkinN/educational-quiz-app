package ru.lobakina.educationalquizapp.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.lobakina.educationalquizapp.service.security.ApplicationUserDetailsService;

import static ru.lobakina.educationalquizapp.support.constants.RoleAccessEndpointConstantsKeeper.*;
import static ru.lobakina.educationalquizapp.support.constants.RoleNameConstantsKeeper.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ApplicationUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(TESTS_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(USERS_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(ADMIN)
                                .requestMatchers(SUBJECTS_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(ADMIN)
                                .requestMatchers(GROUPS_ADMIN_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(ADMIN)
                                .requestMatchers(TESTS_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(TEACHER)
                                .requestMatchers(TEST_STUDENTS_STUDENT_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(STUDENT)
                                .requestMatchers(TEST_GROUPS_TEACHER_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(TEACHER)
                                .requestMatchers(TEST_STUDENTS_TEACHER_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(TEACHER)
                                .requestMatchers(ANSWERS_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(TEACHER)
                                .requestMatchers(USERS_TEACHER_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(TEACHER)
                                .requestMatchers(QUESTION_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(TEACHER)
                                .requestMatchers(USERS_TEACHER_AND_ADMIN_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(TEACHER, ADMIN)
                                .requestMatchers(GROUPS_TEACHER_AND_ADMIN_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(TEACHER, ADMIN)
                                .requestMatchers("/logout").authenticated())
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .permitAll())
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutSuccessUrl("/login")
                                .logoutUrl("/logout")
                                .invalidateHttpSession(true)
                                .permitAll()
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")));
        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}

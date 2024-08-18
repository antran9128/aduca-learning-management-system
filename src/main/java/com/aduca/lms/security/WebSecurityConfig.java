package com.aduca.lms.security;

import com.aduca.lms.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.DispatcherType;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserService userService) {
    return new CustomUserDetailsService(userService);
  }

  @Bean
  public DaoAuthenticationProvider authProvider(
      PasswordEncoder passwordEncoder,
      UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    // authProvider.setHideUserNotFoundExceptions(false);
    return authProvider;
  }

  @Bean
  public AuthenticationSuccessHandler customSuccessHandler() {
    return new CustomSuccessHandler();
  }

  @Bean
  public SpringSessionRememberMeServices rememberMeServices() {
    SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
    // optionally customize
    rememberMeServices.setAlwaysRemember(true);

    return rememberMeServices;
  }

  @Bean
  @Order(1)
  public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/admin/**")
        .authorizeHttpRequests(authz -> authz
            .dispatcherTypeMatchers(DispatcherType.FORWARD,
                DispatcherType.INCLUDE)
            .permitAll()
            .requestMatchers("/homepage", "/admin/login", "/login", "/register", "/client/**", "/admin/css/**",
                "/admin/images/**",
                "/admin/flags/**",
                "/admin/fonts/**", "/admin/js/**", "/admin/plugins/**")
            .permitAll()
            .requestMatchers("/admin/**").hasAuthority("ROLE_Admin")
            .anyRequest().authenticated())
        .sessionManagement((sessionManagement) -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .invalidSessionUrl("/admin/logout?expired")
            .maximumSessions(1)
            .maxSessionsPreventsLogin(false))
        .logout(logout -> logout
            .logoutUrl("/admin/logout")
            .deleteCookies("JSESSIONID").invalidateHttpSession(true))

        .rememberMe(r -> r.rememberMeServices(rememberMeServices()))
        .formLogin(formLogin -> formLogin
            .loginPage("/admin/login")
            .failureUrl("/admin/login?error")
            .successHandler(customSuccessHandler())
            .permitAll())
        .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny"));
    return http.build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain instructorFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/instructor/**")
        .authorizeHttpRequests(authz -> authz
            .dispatcherTypeMatchers(DispatcherType.FORWARD,
                DispatcherType.INCLUDE)
            .permitAll()
            .requestMatchers("/homepage", "/admin/login", "/login", "/register", "/client/**", "/admin/css/**",
                "/admin/images/**",
                "/admin/flags/**",
                "/admin/fonts/**", "/admin/js/**", "/admin/plugins/**")
            .permitAll()
            .requestMatchers("/instructor/**").hasAuthority("ROLE_Instructor")
            .anyRequest().authenticated())
        .sessionManagement((sessionManagement) -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .invalidSessionUrl("/admin/logout?expired")
            .maximumSessions(1)
            .maxSessionsPreventsLogin(false))
        .logout(logout -> logout
            .logoutUrl("/admin/logout")
            .deleteCookies("JSESSIONID").invalidateHttpSession(true))

        .rememberMe(r -> r.rememberMeServices(rememberMeServices()))
        .formLogin(formLogin -> formLogin
            .loginPage("/admin/login")
            .failureUrl("/admin/login?error")
            .successHandler(customSuccessHandler())
            .permitAll())
        .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny"));
    return http.build();
  }

  @Bean
  @Order(3)
  public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
    http.securityMatcher("/**")
        .authorizeHttpRequests(authz -> authz
            .dispatcherTypeMatchers(DispatcherType.FORWARD,
                DispatcherType.INCLUDE)
            .permitAll()
            .requestMatchers("/homepage", "/admin/login", "/login", "/register", "/client/**", "/admin/css/**",
                "/admin/images/**",
                "/admin/flags/**",
                "/admin/fonts/**", "/admin/js/**", "/admin/plugins/**")
            .permitAll()
            .requestMatchers("/user/**").hasAuthority("ROLE_User")
            .anyRequest().authenticated())
        .sessionManagement((sessionManagement) -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .invalidSessionUrl("/logout?expired")
            .maximumSessions(1)
            .maxSessionsPreventsLogin(false))

        .logout(logout -> logout.deleteCookies("JSESSIONID").invalidateHttpSession(true))

        .rememberMe(r -> r.rememberMeServices(rememberMeServices()))
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .failureUrl("/login?error")
            .successHandler(customSuccessHandler())
            .permitAll())
        .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny"));

    return http.build();
  }

}

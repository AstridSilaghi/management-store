package mgmt.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { 
  
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) { 
  
        UserDetails admin = User.withUsername("Admin") 
                .password(encoder.encode("pass123")) 
                .roles("ADMIN", "USER") 
                .build(); 
  
        UserDetails user = User.withUsername("User1") 
                .password(encoder.encode("pass321")) 
                .roles("USER") 
                .build(); 
  
        return new InMemoryUserDetailsManager(admin, user); 
    } 
  
    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        return http.csrf().disable()
        		.headers().frameOptions().disable()
        		.and()
                .authorizeHttpRequests().requestMatchers("/swagger-ui/**", "/v3/api-docs/**").authenticated()
                .and().authorizeHttpRequests().requestMatchers("/auth/admin/**").authenticated()
                .and().authorizeHttpRequests().requestMatchers("/auth/user/**").authenticated()
                .and().authorizeHttpRequests().requestMatchers("/product/**").authenticated()
                .and().authorizeHttpRequests().requestMatchers("/order/**").authenticated()
                .and().authorizeHttpRequests().requestMatchers("/h2-console/**").permitAll()
                .and().formLogin() 
                .and().build(); 
    } 
  
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
  
} 
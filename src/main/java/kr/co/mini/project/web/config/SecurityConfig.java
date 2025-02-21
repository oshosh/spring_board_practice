package kr.co.mini.project.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import kr.co.mini.project.user.entity.User;
// import kr.co.mini.project.user.repository.UserRepository;
// import kr.co.mini.project.web.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (API 요청을 위해)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/users/force-login").permitAll() // force-login 엔드포인트 허용
            .anyRequest().authenticated() // 그 외는 전부 인증 필요
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        )
        .formLogin(form -> form
            .permitAll()
        )
        .logout(logout -> logout
            .permitAll()
        );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // @Bean
  // public UserDetailsService userDetailsService(UserRepository userRepository) {
  //   return username -> {
  //       User user = userRepository.findByName(username)
  //           .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
  //       return new MyUserDetailsService(user);
  //   };
  // }
}
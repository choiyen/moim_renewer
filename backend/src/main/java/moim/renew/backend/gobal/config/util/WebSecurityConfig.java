package moim.renew.backend.gobal.config.util;

import moim.renew.backend.gobal.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private static final String[] PASS_PATH = {
            "/api/Moim/**",
            "/api/user/**",
            "/api/Auth/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(
                        sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/category/**").permitAll()
                        .requestMatchers(PASS_PATH).permitAll()
                        .anyRequest().authenticated()
                );

        //로그인, 환율, 소켓만 비로그인 시 접속 가능 나머지는 jwt 토큰 필요
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // cors 설정 정의
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:5173", "http://localhost:9000"));  // 특정 도메인 허용
        config.setAllowedMethods(Arrays.asList("HEAD", "POST", "GET", "DELETE", "PUT", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));  // 🔹 허용할 헤더 추가
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type")); // 🔹 클라이언트에서 접근 가능하도록 노출

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
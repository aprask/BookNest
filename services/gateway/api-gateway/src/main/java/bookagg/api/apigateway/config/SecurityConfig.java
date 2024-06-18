package bookagg.api.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final GatewayFilter authenticationRedirectFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .authorizeExchange(exchanges ->
                        exchanges
                                .pathMatchers("/register").permitAll()
                                .anyExchange().authenticated()
                )
                .oauth2Login(withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("dashboard-service", r -> r.path("/dash/**")
                        .filters(f -> f.filter(authenticationRedirectFilter))
                        .uri("lb://dashboard-service"))
                .route("notification-service", r -> r.path("/notification/**")
                        .filters(f -> f.filter(authenticationRedirectFilter))
                        .uri("lb://notification-service"))
                .route("recommendation-service", r -> r.path("/recommendation/**")
                        .filters(f -> f.filter(authenticationRedirectFilter))
                        .uri("lb://recommendation-service"))
                .route("userdetails-service", r -> r.path("/userdetails/**")
                        .filters(f -> f.filter(authenticationRedirectFilter))
                        .uri("lb://userdetails-service"))
                .build();
    }

}

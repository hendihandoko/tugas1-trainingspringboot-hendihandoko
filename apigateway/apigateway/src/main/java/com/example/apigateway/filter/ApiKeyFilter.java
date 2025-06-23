package com.example.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Objects;

@Component
@Order(-2)
public class ApiKeyFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiKeyFilter.class);
    private static final String API_KEY_HEADER = "X-API-KEY";
    private static final String ALLOWED_API_KEY = "123";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1. Ambil list nilai dari header. Header bisa memiliki banyak nilai, jadi hasilnya List.
        List<String> apiKeyHeaders = exchange.getRequest().getHeaders().get(API_KEY_HEADER);

        boolean apiKeyIsValid = false;

        // 2. Cek apakah header ada dan tidak kosong
        if (apiKeyHeaders != null && !apiKeyHeaders.isEmpty()) {
            // Ambil nilai pertama dari header dan bandingkan
            String apiKey = apiKeyHeaders.get(0);
            if (Objects.equals(apiKey, ALLOWED_API_KEY)) {
                apiKeyIsValid = true;
            }
        }

        // 3. Jika setelah semua pengecekan API key tidak valid, tolak request.
        if (!apiKeyIsValid) {
            log.warn("Request Ditolak! API Key tidak valid atau tidak ada. Path: {}", exchange.getRequest().getPath());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 4. Jika valid, log dan lanjutkan.
        log.info("Validasi API Key berhasil. Meneruskan request ke: {}", exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
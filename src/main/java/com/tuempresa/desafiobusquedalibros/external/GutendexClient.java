package com.tuempresa.desafiobusquedalibros.external;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.tuempresa.desafiobusquedalibros.external.dto.GutendexResponse;

@Service
public class GutendexClient {
    private  final String URL_BASE = "https://gutendex.com/books/?=";
    private final String API_KEY = "&apikey=cf3ada4f";

    public Mono<GutendexResponse> buscarPorTitulo(String title) {
        return WebClient.create(URL_BASE + "search=" + title + API_KEY)
                .get()
                .retrieve()
                .bodyToMono(GutendexResponse.class);
    }
}

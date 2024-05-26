package com.common.service;

import com.common.utils.Crypto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Retryable(maxAttempts = 3, backoff = @Backoff(random = true, maxDelay = 1000))
public class RestClientServiceImpl implements ClientService {

    @Value("${internal.auth.key}")
    private String authKey;

    private final String internalServerData = "SDFILSDJFLAISEJFALFSI";

    public <T> ResponseEntity<T> get(String url, Class<T> responseType) throws Exception {
        Consumer<HttpHeaders> headersConsumer = setHeader(internalServerData);
        RestClient client = RestClient.create();
        return client.get().uri(url).headers(headersConsumer).retrieve().toEntity(responseType);
    }

    public <T, V> ResponseEntity<V> post(String url, T body, Class<V> responseType) throws Exception {
        RestClient client = RestClient.create();
        return client.post().uri(url).headers(setHeader(internalServerData)).body(body).retrieve().toEntity(responseType);
    }

    private Consumer<HttpHeaders> setHeader(String internalServerData) throws Exception {
        return headers -> {
            headers.set("Content-Type", "application/json");
                try {
                    String secretKey = Crypto.aesEcbEncrypt(authKey, internalServerData);
                    headers.set("Authorization", secretKey);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        };
    }
}

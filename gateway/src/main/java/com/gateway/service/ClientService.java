package com.gateway.service;

import com.gateway.PrincipalDetail;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    <T> ResponseEntity<T> get(PrincipalDetail user, String url, Class<T> responseType) throws Exception;
    <T, V> ResponseEntity<V>  post(PrincipalDetail user, String url, T body, Class<V> responseType) throws Exception;
}

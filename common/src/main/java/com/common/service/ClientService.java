package com.common.service;

import org.springframework.http.ResponseEntity;

public interface ClientService {
    <T> ResponseEntity<T> get(String url, Class<T> responseType) throws Exception;
    <T, V> ResponseEntity<V>  post(String url, T body, Class<V> responseType) throws Exception;
}

package com.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final MessageSource ms;

    @ExceptionHandler(ElementNotFoundException.class)
    protected ResponseEntity<String> handleElementNotFoundException(ElementNotFoundException e) {
        return ResponseEntity.status(400).body(ms.getMessage("element.notFound", null, LocaleContextHolder.getLocale()));
    }

    @ExceptionHandler(RegistrationException.class)
    protected ResponseEntity<String> handleElementNotFoundException(RegistrationException e) {
        return ResponseEntity.status(409).body(ms.getMessage("user.duplicate", null, LocaleContextHolder.getLocale()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(400).body(e.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(500).body("server error");
    }
}

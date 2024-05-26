package com.common.aop;

import com.common.utils.Crypto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@RequiredArgsConstructor
public class InternalAuthCheckAspect {

    @Value("${internal.auth.key}")
    private String authKey;

    @Before(value = "@within(com.common.aop.InternalAuthCheck)")
    public void check() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authorization = request.getHeader("authorization");
        if (authorization.isBlank()) {
            throw new BadRequestException();
        }
        String ecbDecrypt = Crypto.aesEcbDecrypt(authKey, request.getHeader("authorization"));
        System.out.println(ecbDecrypt);
    }
}

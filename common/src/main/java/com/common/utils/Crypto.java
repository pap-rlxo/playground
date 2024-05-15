package com.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static io.netty.util.CharsetUtil.UTF_8;

public class Crypto {

   static public String aesEcbEncrypt(String raw, String value) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw.getBytes(UTF_8), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] bytes = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    static public String aesEcbDecrypt(String raw, String encrypted) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw.getBytes(UTF_8), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(bytes);
    }
}

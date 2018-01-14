package com.altarix.services.security;

import com.altarix.dtos.security.LoginInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

@Service
public class TokenHandlerImpl implements TokenHandler {
    private final SecretKey secretKey;
    private final static String key = "jwtkey123";
    public TokenHandlerImpl() {
        byte[] decodedKey = Base64.getEncoder().encode(key.getBytes());
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    @Override
    public Optional<LoginInfo> extractLoginInfo(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return Optional.ofNullable(body.getId()).map(name -> {
                return new LoginInfo(name, Instant.now());
            });
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}

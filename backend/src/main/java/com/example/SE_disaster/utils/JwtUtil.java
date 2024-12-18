package com.example.SE_disaster.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String KEY = "poetryfactory";

    private static final String ISSUER = "pf";

    public static String genToken(Map<String, Object> claims) {
        try {
            Date expireDate = new Date(System.currentTimeMillis() + 3600 * 1000); // 过期时间
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim("claims", claims)
                    .withExpiresAt(expireDate)
                    .sign(Algorithm.HMAC256(KEY));
        } catch (Exception e) {
            throw new RuntimeException("Token生成错误");
        }
    }

    public Map<String, Claim> verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY))
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token验证失败");
        }
    }
}

package com.example.fuzz.util;

import com.example.fuzz.config.Audience;
import com.example.fuzz.common.GlobalException;
import com.example.fuzz.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/27 15:59
 */
@Component
public class JwtTokenUtil {
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    @Autowired
    private Audience audience;

    public JwtTokenUtil() {
    }

    public Claims parseJwt(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(audience.getBase64Secret())
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }catch (Exception exception){
            throw new GlobalException("token 验证失败");
        }
    }

    public String createJwt(Long userId, String username) {
        Date now = new Date(System.currentTimeMillis());
        JwtBuilder builder = Jwts.builder()
                .setId(userId.toString())
                .claim("userId", userId)
                .setSubject(username)
                .setIssuer(audience.getClientId())
                .setIssuedAt(new Date())
                .setAudience(audience.getName())
                .signWith(SignatureAlgorithm.HS256, audience.getBase64Secret());
        int TTLMillis = audience.getExpiresSecond();
        if (TTLMillis >= 0) {
            Date exp = new Date(System.currentTimeMillis() + TTLMillis);
            builder.setExpiration(exp)  // 过期时间
                    .setNotBefore(now); // 开始时间
        }
        return builder.compact();
    }

    public String getUsername(String token) {
        return parseJwt(token).getSubject();
    }

    public HashMap<String, Object> formatTokenInfo(String token, User user) {
        HashMap<String, Object> info = new HashMap<>();
        info.put("access_token", token);
        info.put("expires_in", audience.getExpiresSecond());
        info.put("user_info", user);
        return info;
    }
}

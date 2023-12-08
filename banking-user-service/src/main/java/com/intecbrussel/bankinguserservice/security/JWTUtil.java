package com.intecbrussel.bankinguserservice.security;

import com.intecbrussel.bankinguserservice.user.model.AuthUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {

    private final String secret_key = "mysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkey";

    private final long accessTokenValidity = 60*60*1000;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public JWTUtil() {
        this.jwtParser = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8))).build();
    }

    public String createToken(AuthUser user, String role){

        Claims claims = Jwts.claims().setSubject(user.getEmailAddress());
        claims.put("role", "ROLE_" + role);

        Date tokenCreateDate = new Date();
        Date tokenValidity = new Date(tokenCreateDate.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        }catch (ExpiredJwtException expiredJwtException){
            req.setAttribute("expired", expiredJwtException.getMessage());
            throw expiredJwtException;
        } catch (Exception expiredJwtException) {
            req.setAttribute("invalid", expiredJwtException.getMessage());
            throw expiredJwtException;
        }
    }

    public String resolveToken(HttpServletRequest request){

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {

        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }


}


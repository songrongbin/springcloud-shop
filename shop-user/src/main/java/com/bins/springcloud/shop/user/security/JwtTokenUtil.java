package com.bins.springcloud.shop.user.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Date;

public class JwtTokenUtil {

    private static final String USERNAME = Claims.SUBJECT;
    public static final String SUBJECT = "BINS";
    private static final String CREATED = "created";
    private static final String AUTHORITIES = "authorities";
    private static final String USERID = "userId";
    private static final String SECRET = "BINS.COM";
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
    public static String TOKEN_SUP = "BINS";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static String  generateToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        StringBuffer as = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            as.append(authority.getAuthority()).append(",");
        }
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String jwt = Jwts.builder()
                .setSubject(SUBJECT)
                .claim("authorities", as.toString())
                .claim("id", customUserDetails.getUserId())
                .claim("username", customUserDetails.getUsername())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 获取用户权限
     * @param token
     * @return
     */
    public static String getAuthorities(String token) {
        String authority;
        try {
            Claims claims = getClaimsFromToken(token);
            authority = claims.get("authorities").toString();
        } catch (Exception e) {
            authority = null;
        }
        return authority;
    }

    /**
          * 获取用户名
          * @param token
          * @return
          */
    public static String getUsername(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.get("username").toString();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        Long userId;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = (Long)claims.get("id");
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    public static Long getUserId() {
        Authentication authentication = getAuthentication();
        return getUserId(authentication);
    }

    public static Long getUserId(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
        return customUser.getUserId();
    }

    public static Authentication getAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return null;
        }
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication;
    }

    public static Boolean validateToken(String token, String username) {
        String userName = getUsername(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }

    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Claims claims = getClaimsFromToken(token);
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
}

package com.attendance.security;

import com.attendance.model.User;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class AuthTokenStore {
    public record Principal(String username, String role) {}
    private record Session(Principal principal, Instant expiresAt) {}

    private final SecureRandom random = new SecureRandom();
    private final ConcurrentMap<String, Session> tokens = new ConcurrentHashMap<>();

    public String issue(User user) {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        tokens.put(token, new Session(new Principal(user.username, user.role), Instant.now().plusSeconds(43_200)));
        return token;
    }

    public Principal find(String token) { Session session=tokens.get(token);if(session==null)return null;if(!session.expiresAt().isAfter(Instant.now())){tokens.remove(token,session);return null;}return session.principal(); }
    public void revoke(String token) { if (token != null) tokens.remove(token); }
}

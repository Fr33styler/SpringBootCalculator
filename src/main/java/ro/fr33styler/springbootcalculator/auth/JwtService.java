package ro.fr33styler.springbootcalculator.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final long EXPIRATION_MS = TimeUnit.MINUTES.toMillis(15);

    private final SecretKey privateKey = Jwts.SIG.HS256.key().build();

    public String createToken(String name) {
        return generateToken(name).compact();
    }

    public String createToken(String name, Map<String, Object> claims) {
        return generateToken(name).claims(claims).compact();
    }

    private JwtBuilder generateToken(String name) {
        return Jwts.builder()
                .subject(name)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(privateKey);
    }

    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        Claims claims = extractClaims(token);

        return userDetails.getUsername().equals(claims.getSubject()) && claims.getExpiration().after(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(privateKey).build().parseSignedClaims(token).getPayload();
    }

}
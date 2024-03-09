package shop.titupet.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import shop.titupet.models.entities.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final String SECRET_KEY = "034c3faf5961d9061cd1a14ef9f7012a58ef565714aae0850de136aa9b3f4f74";
    private final long jwtExpiration = 86400000L;
    private final long refreshExpiration = 604800000L;

    public String extractUsername(String token) {

        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            User userDetails
    ){
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            User userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            User user,
            Long jwtExpiration
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("fullName", user.getFullName())
                .claim("role", user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public Claims decodeToken(String token) {
        return extractAllClaims(token);
    }


    private Claims extractAllClaims(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(getSignInKey()).build()
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error decoding token: " + e.getMessage());
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

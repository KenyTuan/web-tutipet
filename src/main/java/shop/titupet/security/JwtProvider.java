//package shop.titupet.config.security;
//
//import io.jsonwebtoken.*;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import shop.titupet.models.entities.CustomUserDetails;
//import shop.titupet.models.entities.User;
//import shop.titupet.service.AuthorizeService;
//
//import java.util.Date;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtProvider {
//
//    private static final String JWT_SECRET = "1@saj#ssKIT_Jany";
//
//    private static final Long JWT_EXPIRATION = 259200L;
//
//    public static String generateToken(CustomUserDetails user) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//
//        return Jwts.builder()
//                .setSubject(Long.toString(user.getUser().getId()))
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//                .compact();
//    }
//
//    public static String getEmailFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
//                                        .parseClaimsJwt(token)
//                                        .getBody();
//        return claims.getSubject();
//    }
//
//    public static boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJwt(token);
//            return true;
//        }catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        }
//        return false;
//    }
//}

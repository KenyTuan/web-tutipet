package shop.titupet.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import shop.titupet.models.entities.User;
import shop.titupet.service.AuthorizeService;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static String AUTHORIZATION_HEADER = "Authorization";
    public static String AUTHORIZATION_PREFIX = "Bearer ";

    private final AuthorizeService authorizeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (token != null && token.startsWith(AUTHORIZATION_PREFIX)) {
            token = token.replace(AUTHORIZATION_HEADER, "");

            try {
                //Get email from token
                String email = JwtProvider.getEmailFromToken(token);

                if (email != null && JwtProvider.validateToken(token)) {

                    User user = authorizeService.findByEmail(email)
                            .orElseThrow(() -> new Exception("User not existed."));
                    //Get user from email and check if user existed
                    Long userId = user.getId();

                    //Set user infor into context
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, token, List.of(
                            new SimpleGrantedAuthority("admin")
                    ));
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    throw new Exception("JWT has expired or invalid.");
                }
            } catch (Exception ex) {
                log.info(ex.getMessage());
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}

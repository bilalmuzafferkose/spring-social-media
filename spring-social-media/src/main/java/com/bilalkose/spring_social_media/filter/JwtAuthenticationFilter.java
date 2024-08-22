package com.bilalkose.spring_social_media.filter;

import com.bilalkose.spring_social_media.service.JwtService;
import com.bilalkose.spring_social_media.service.UserDetailsImpl;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //AuthenticationProdiver imiz aslinda burasi
    //Gelen request icindeki tokeni validate edecek gorevi budur

    private final JwtService jwtService;
    private final UserDetailsImpl userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Authorization başlığını alır
        String authHeader = request.getHeader("Authorization");

        // Eğer Authorization başlığı yoksa veya "Bearer " ile başlamıyorsa, bu isteği filtre zincirinde bir sonraki filtreye iletir
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        // Kullanıcı adı mevcut ve şu anda güvenlik bağlamında kimlik doğrulama yapılmamışsa devam eder, içine girer
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) { //username var ve token validate durumda
            //o halde authentication bilgisini context e atayim ki tekrardan bu adimlara gelmesin
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Token geçerliyse
            if(jwtService.isValid(token, userDetails)) {
                // UsernamePasswordAuthenticationToken oluşturur
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                // Detayları ekler ve güvenlik bağlamına bu kimlik doğrulama token'ını atar
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // İsteği filtre zincirinde bir sonraki filtreye iletir
        filterChain.doFilter(request, response);


    }
}

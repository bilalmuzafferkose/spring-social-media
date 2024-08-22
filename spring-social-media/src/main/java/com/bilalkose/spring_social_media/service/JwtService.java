package com.bilalkose.spring_social_media.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    //Jwt token uretmemi saglayan bir servis

    private final String SECRET_KEY = "185f336db632e083a16e5536c2a7224f05b717e1aa630c7158494e24db221768"; //bu key ile tokenimi sifreleyip karsi tarafa sifreli sekilde iletiyorum
    //SHA-256 hash
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user) { //gelen tokeni validate etmeliyiz. onemli olan username ve expired date validate etmek gerekiyor. //jwt filter kullaniyor
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
        //Bu metot, verilen token'ın geçerli olup olmadığını kontrol eder. Token'ın sahibi olan kullanıcı adı ile UserDetails içindeki kullanıcı adı eşleşmeli ve token süresi dolmamış olmalıdır.
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    } //token ne zaman expired olacak

    public <T> T extractClaim(String token, Function<Claims,T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        extraClaims.put("role", userDetails.getAuthorities()); // Role bilgisini ekliyoruz
        extraClaims.put("test","testValue"); //payload a extra keyvalue cifti
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    //claim -> payload

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) //username i verdigimiz yer
                .setIssuedAt(new Date(System.currentTimeMillis())) //ne zaman token uretildi
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //ne zaman token gecersiz olacak
                .signWith(getSigninKey(), SignatureAlgorithm.HS256) //keyi kullandigimiz yer
                .compact();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}


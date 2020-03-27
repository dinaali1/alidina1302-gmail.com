package com.backendSOA.backendSOA.configurations;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;
import com.google.gson.JsonObject;
import com.backendSOA.backendSOA.models.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private String secret = "qm5-cPQ70qO1hvYQ8MPkm79FRdnVSwqcpj89YWM54NtZbwCdPGHL4aJrhCq6WTFOTAT_hZoAO1h3R56Og6NpqA";

    public String getUsernameFromToken(String token) {

        try {
            return getClaimFromToken(token, Claims::getSubject);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);

    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new Error("invalid token");
        }
    }

    public String generateToken(User user) {
        JsonObject userJSON = new JsonObject();
        userJSON.addProperty("email", user.getEmail());
        userJSON.addProperty("firstName", user.getFirstName());
        userJSON.addProperty("lastName", user.getLastName());
        userJSON.addProperty("password", user.getPassword());
        return doGenerateToken( userJSON.toString());

    }

    public String doGenerateToken(String subject) {

        return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

}

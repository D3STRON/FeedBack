package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

public class JWTConfig {

    private String id,  password, subject;
    private long ttlMillis =3000000;


    public void setId(String id) {
        this.id = id;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String createJWT() {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = new byte[0];
        try {
            apiKeySecretBytes = "secret".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ;
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(this.id)
                .setSubject(this.subject)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Claims parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        byte[] apiKeySecretBytes = new byte[0];
        try {
            apiKeySecretBytes = "secret".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Jwts.parser()
                .setSigningKey(apiKeySecretBytes)
                .parseClaimsJws(jwt).getBody();
    }

    public JWTConfig() {
    }
}

package dev.vidhya.productservice.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TokenValidator {
    RestTemplateBuilder restTemplateBuilder;

    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public JWTResponse ValidateToken(ValidateSessionTokenDto validateSessionTokenDto){
        //call user service
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JWTResponse> jwtResponse = restTemplate.postForEntity("http://localhost:3001/auth/validate", validateSessionTokenDto,JWTResponse.class);

        if(jwtResponse == null || jwtResponse.getBody() == null)
            throw new RuntimeException("Access Denied");

        return jwtResponse.getBody();
    }
}

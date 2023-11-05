package dev.naman.productservice.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidator {
    RestTemplateBuilder restTemplateBuilder;

    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public JWTResponse ValidateToken(Long userId, String token){
        //call user service
        ValidateSessionTokenDto validateSessionTokenDto = new ValidateSessionTokenDto();
        validateSessionTokenDto.setUserId(userId);
        validateSessionTokenDto.setToken(token);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JWTResponse> jwtResponse = restTemplate.postForEntity("http://localhost:3001/auth/validate", validateSessionTokenDto,JWTResponse.class);

        if(jwtResponse == null || jwtResponse.getBody() == null)
            throw new RuntimeException("Access Denied");
//            return null;

        return jwtResponse.getBody();
    }
}

package dev.vidhya.productservice.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateSessionTokenDto {
    private Long userId;
    private String token;
}

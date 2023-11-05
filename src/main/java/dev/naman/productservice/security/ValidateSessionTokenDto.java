package dev.naman.productservice.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateSessionTokenDto {
    private Long userId;
    private String token;
}

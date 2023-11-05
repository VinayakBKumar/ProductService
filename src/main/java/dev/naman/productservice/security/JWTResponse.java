package dev.naman.productservice.security;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JWTResponse {
    private Long userId;
    private String name;
    private String email;
    private Set<Role> roles;
}

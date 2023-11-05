package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.security.JWTResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
//    void setJWTResponse(JWTResponse jwtResponse);

    GenericProductDto createProduct(GenericProductDto product);

//    GenericProductDto getProductById(JWTResponse jwtResponse, UUID uuid) throws NotFoundException;
//    GenericProductDto getProductById(JWTResponse jwtResponse, Long id) throws NotFoundException;
    GenericProductDto getProductById(UUID uuid) throws NotFoundException;
    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(UUID uuid);
    GenericProductDto deleteProduct(Long id);

    GenericProductDto updateProductById(UUID uuid, GenericProductDto genericProductDto) throws NotFoundException;
    GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException;



}

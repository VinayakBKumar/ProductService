package dev.vidhya.productservice.services;

import dev.vidhya.productservice.dtos.GenericProductDto;
import dev.vidhya.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    List<GenericProductDto> getAllProducts();
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto updateProductById(Long id, GenericProductDto product);
    GenericProductDto deleteProductById(Long id);
}

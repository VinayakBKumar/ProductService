package dev.vidhya.productservice.services;

import dev.vidhya.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfStoreProductService")
public class SelfStoreProductService implements ProductService{

    public List<GenericProductDto> getAllProducts(){
        return null;
    }
    public GenericProductDto getProductById(Long id){
        return new GenericProductDto();
        //return "Self: " + id;
    }

    public GenericProductDto createProduct(GenericProductDto product){
        return new GenericProductDto();
    }

    public GenericProductDto updateProductById(Long id, GenericProductDto product){
        return new GenericProductDto();
    }

    public GenericProductDto deleteProductById(Long id) {
        return new GenericProductDto();
    }
}

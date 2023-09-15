package dev.vidhya.productservice.services;

import dev.vidhya.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.vidhya.productservice.dtos.GenericProductDto;
import dev.vidhya.productservice.exceptions.NotFoundException;
import dev.vidhya.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    public List<GenericProductDto> getAllProducts(){
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            genericProductDtos.add(getGenericProductDto(fakeStoreProductDto));
        }

        return genericProductDtos;
    }

    public GenericProductDto createProduct(GenericProductDto product){
        return getGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return getGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }


    public GenericProductDto updateProductById(Long id, GenericProductDto product){
        return getGenericProductDto(fakeStoreProductServiceClient.updateProductById(id, product));
    }

    public GenericProductDto deleteProductById(Long id){
       return getGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }


    public GenericProductDto getGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setUuid(fakeStoreProductDto.getUuid());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());

        return genericProductDto;
    }
}

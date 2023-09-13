package dev.vidhya.productservice.services;

import dev.vidhya.productservice.dtos.FakeStoreProductDto;
import dev.vidhya.productservice.dtos.GenericProductDto;
import dev.vidhya.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    RestTemplateBuilder restTemplateBuilder;
    String fakeStoreGetAllProductUrl = "https://fakestoreapi.com/products";
    String fakeStoreGetProductUrl = "https://fakestoreapi.com/products/{id}";
    String fakeStoreCreateProductUrl = "https://fakestoreapi.com/products";
    String fakeStorePatchProductUrl = "https://fakestoreapi.com/products/{id}";
    String fakeStoreDeleteProductUrl = "https://fakestoreapi.com/products/{id}";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<GenericProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(fakeStoreGetAllProductUrl, FakeStoreProductDto[].class);

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for( FakeStoreProductDto fakeStoreProductDto: responseEntity.getBody() ){
            genericProductDtos.add(getGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(fakeStoreCreateProductUrl, product, FakeStoreProductDto.class);

        return getGenericProductDto(responseEntity.getBody());

        //return genericProductDto;
    }
    public GenericProductDto getProductById(Long id) throws NotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(fakeStoreGetProductUrl, FakeStoreProductDto.class,id);

        if(responseEntity.getBody() == null){
            throw new NotFoundException("Product id: " + id + " does not exists.");
        }
        return getGenericProductDto(responseEntity.getBody());

        /*
        GenericProductDto product = new GenericProductDto();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        */

        /*Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        */
        //return product;
        //return "Fake: " + id;
    }


    public GenericProductDto updateProductById(Long id, GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.exchange(fakeStorePatchProductUrl, HttpMethod.PUT,  new HttpEntity<GenericProductDto>(product), FakeStoreProductDto.class, id);

        return getGenericProductDto(fakeStoreProductDto.getBody());

    }

    public GenericProductDto deleteProductById(Long id){
        /*RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =  restTemplate.exchange(fakeStoreDeleteProductUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);

        return getGenericProductDto(responseEntity.getBody());*/

        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(fakeStoreDeleteProductUrl, HttpMethod.GET, requestCallback, responseExtractor, id);

        return getGenericProductDto(responseEntity.getBody());
    }


    public GenericProductDto getGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());

        return genericProductDto;
    }

}

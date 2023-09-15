package dev.vidhya.productservice.thirdpartyclients.productservice.fakestore;

import dev.vidhya.productservice.dtos.GenericProductDto;
import dev.vidhya.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductServiceClient {

    RestTemplateBuilder restTemplateBuilder;

    private String fakeStoreProductIdUrl;
    private String fakeStoreProductsUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakeStoreApiUrl
            , @Value("${fakestore.api.paths.product}") String fakeStoreProductApiPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreProductIdUrl = fakeStoreApiUrl + fakeStoreProductApiPath + "/{id}";
        fakeStoreProductsUrl =  fakeStoreApiUrl + fakeStoreProductApiPath;
    }

    public FakeStoreProductDto[] getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(fakeStoreProductsUrl, FakeStoreProductDto[].class);

        return responseEntity.getBody();
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(fakeStoreProductsUrl, product, FakeStoreProductDto.class);

        return responseEntity.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(fakeStoreProductIdUrl, FakeStoreProductDto.class,id);

        if(responseEntity.getBody() == null){
            throw new NotFoundException("Product id: " + id + " does not exists.");
        }
        return responseEntity.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id, GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(fakeStoreProductIdUrl, HttpMethod.PUT,  new HttpEntity<GenericProductDto>(product), FakeStoreProductDto.class, id);

        return responseEntity.getBody();
    }

    public FakeStoreProductDto deleteProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(fakeStoreProductIdUrl, HttpMethod.GET, requestCallback, responseExtractor, id);

        return responseEntity.getBody();
    }
}

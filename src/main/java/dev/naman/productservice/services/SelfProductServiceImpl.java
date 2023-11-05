package dev.naman.productservice.services;

import dev.naman.productservice.dtos.convertor.ProductConvertor;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import dev.naman.productservice.security.JWTResponse;
import dev.naman.productservice.security.Role;
import dev.naman.productservice.security.RoleType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Repository("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {

//    private JWTResponse jwtResponse;

    ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

//    public void setJWTResponse(JWTResponse jwtResponse){
//        this.jwtResponse = jwtResponse;
//    }
//
//    public JWTResponse getJWTResponse(JWTResponse jwtResponse){
//        return this.jwtResponse;
//    }

//    @Override
//    public GenericProductDto getProductById(JWTResponse jwtResponse, UUID uuid) {
//        Role role = new Role();
//        role.setId(2l);
//        role.setRole(RoleType.USER.name());
//        if(!jwtResponse.getRoles().contains(role))
//            throw new RuntimeException("Unauthorized Access");
//
//        Product product = productRepository.findById(uuid).get();
//
//        return ProductConvertor.getGenericProductDto(product);
//    }
//
//    @Override
//    public GenericProductDto getProductById(JWTResponse jwtResponse, Long id) throws NotFoundException {
//        return null;
//    }

    @Override
    public GenericProductDto getProductById(UUID uuid) {
        Role role = new Role();
        role.setId(2l);
        role.setRole(RoleType.USER.name());

        Product product = productRepository.findById(uuid).get();

        return ProductConvertor.getGenericProductDto(product);
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = ProductConvertor.getProductDto(genericProductDto);

        productRepository.save(product);

//        Product product1 = new Product();


//        product1.setTitle(genericProductDto.getTitle());
//        product1.setDescription(genericProductDto.getDescription());
//        Category category = new Category();
//        category.setName(genericProductDto.getCategory());
//        product1.setCategory(category);
//        Price price = new Price();
//        price.setPrice(genericProductDto.getPrice());
//        product1.setPrice(price);
//        product1.setImage(genericProductDto.getImage());
//
//        productRepository.save(product1);

        return ProductConvertor.getGenericProductDto(product);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        for(Product product : products){
            genericProductDtos.add(ProductConvertor.getGenericProductDto(product));
        }

        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(UUID uuid) {

        productRepository.deleteById(uuid);
        return new GenericProductDto();
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(UUID uuid, GenericProductDto genericProductDto) throws NotFoundException{
        //Product product = ProductConvertor.getProductDto(genericProductDto);
        Product product = productRepository.getById(uuid);

        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        Category category = new Category();
        category.setName(genericProductDto.getCategory());
        product.setCategory(category);
        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        product.setPrice(price);
        product.setImage(genericProductDto.getImage());

        productRepository.save(product);
        return ProductConvertor.getGenericProductDto(product);
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
        return null;
    }


}

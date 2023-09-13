package dev.vidhya.productservice.dtos;

import dev.vidhya.productservice.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto extends BaseModel {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}

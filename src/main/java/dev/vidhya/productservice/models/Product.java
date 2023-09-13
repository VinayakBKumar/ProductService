package dev.vidhya.productservice.models;

import lombok.*;

@Getter
@Setter
/*@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder*/
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
}

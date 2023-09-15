package dev.vidhya.productservice.thirdpartyclients.productservice.fakestore;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FakeStoreProductDto {
    private UUID uuid;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}

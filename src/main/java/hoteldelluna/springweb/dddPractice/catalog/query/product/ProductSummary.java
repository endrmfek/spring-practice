package hoteldelluna.springweb.dddPractice.catalog.query.product;

import lombok.Getter;

@Getter
public class ProductSummary {
    private String id;
    private String name;
    private int price;
    private String image;

    public ProductSummary(String id, String name, int price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }


}

package hoteldelluna.springweb.dddPractice.catalog.command.domain.product;

import hoteldelluna.springweb.dddPractice.catalog.command.domain.category.CategoryId;
import hoteldelluna.springweb.dddPractice.common.jpa.MoneyConverter;
import hoteldelluna.springweb.dddPractice.common.model.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="d_prodect")
public class Product {
    @EmbeddedId
    private ProductId id;

    @ElementCollection(fetch = FetchType.LAZY) // List 타입은 RDB에서 표현할수 없음. 그래서 따로 테이블을 만들어서 관리함.
    @CollectionTable(name = "product_category",
                    joinColumns = @JoinColumn(name = "product_id"))
    private Set<CategoryId> categoryIds;

    private String name;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    private String detail;

    @OneToMany(cascade = {CascadeType.PERSIST , CascadeType.REMOVE},
    orphanRemoval = true , fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @OrderColumn(name = "list_idx")
    private List<Image> images =new ArrayList<>();

    protected Product() {
    }

    public Product(ProductId id, String name, Money price, String detail, List<Image> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.images.addAll(images);
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public String getDetail() {
        return detail;
    }

    public List<Image> getImages() {
        return Collections.unmodifiableList(images);
    }

    public void changeImages(List<Image> newImages) {
        images.clear();
        images.addAll(newImages);
    }

    public String getFirstImageThumbnailPath() {
        if (images == null || images.isEmpty()) return null;
        return images.get(0).getThumbnailUrl();
    }
}

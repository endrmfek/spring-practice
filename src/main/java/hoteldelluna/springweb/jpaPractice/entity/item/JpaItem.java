package hoteldelluna.springweb.jpaPractice.entity.item;

import hoteldelluna.springweb.jpaPractice.entity.JpaCategory;
import hoteldelluna.springweb.jpaPractice.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // 이게 뭘까?
@Getter @Setter
public abstract class JpaItem {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price; //가격
    private int stockQuantity; //수량

    @ManyToMany(mappedBy = "jpaItems")
    private List<JpaCategory> categories = new ArrayList<>();

    //비지니스 로직 <- 이걸 왜 entity에만들까?..
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}

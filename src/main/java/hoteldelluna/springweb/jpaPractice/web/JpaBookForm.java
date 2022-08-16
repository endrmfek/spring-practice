package hoteldelluna.springweb.jpaPractice.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JpaBookForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}

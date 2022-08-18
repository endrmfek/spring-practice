package hoteldelluna.springweb.dddPractice.catalog.common.domain.category;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "d_category")
public class Category {
    @EmbeddedId
    private CategoryId id;

    @Column(name = "name")
    private String name;

    protected Category() {
    }

    public Category(CategoryId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

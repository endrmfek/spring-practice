package hoteldelluna.springweb.dddPractice.catalog.query.category;

import hoteldelluna.springweb.dddPractice.catalog.command.domain.category.CategoryId;
import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "d_category")
@Getter
public class CategoryData {
    @EmbeddedId
    private CategoryId id;

    private String name;

    protected CategoryData() {}

    public CategoryData(CategoryId id, String name) {
        this.id = id;
        this.name = name;
    }
}

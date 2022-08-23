package hoteldelluna.springweb.dddPractice.catalog.query.product;

import hoteldelluna.springweb.dddPractice.catalog.query.category.CategoryData;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryProduct {
    private CategoryData category; // 조회 전용데이터.

    private List<ProductSummary> items;
    private int page;
    private int size;
    private long totalCount;
    private int totalPage;

    public CategoryProduct(CategoryData category, List<ProductSummary> items, int page, int size, long totalCount, int totalPage) {
        this.category = category;
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }
}

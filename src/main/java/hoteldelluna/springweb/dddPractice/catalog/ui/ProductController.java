package hoteldelluna.springweb.dddPractice.catalog.ui;

import hoteldelluna.springweb.dddPractice.catalog.query.category.CategoryData;
import hoteldelluna.springweb.dddPractice.catalog.query.category.CategoryDataDao;
import hoteldelluna.springweb.dddPractice.catalog.query.product.CategoryProduct;
import hoteldelluna.springweb.dddPractice.catalog.query.product.ProductData;
import hoteldelluna.springweb.dddPractice.catalog.query.product.ProductQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private CategoryDataDao categoryDataDao;
    private ProductQueryService productQueryService;

    public ProductController(CategoryDataDao categoryDataDao, ProductQueryService productQueryService) {
        this.categoryDataDao = categoryDataDao;
        this.productQueryService = productQueryService;
    }

    @RequestMapping("/ddd/categories")
    public String categories(ModelMap model) {
        List<CategoryData> categories = categoryDataDao.findAll();
        model.addAttribute("categories" , categories);
        return "ddd/category/categoryList";
    }

    @RequestMapping("/ddd/categories/{categoryId}")
    public String list(@PathVariable("categoryId") Long categoryId,
                       @RequestParam(name="page", required = false, defaultValue = "1") int page,
                       ModelMap model) {
        CategoryProduct productInCategory = productQueryService.getProductInCategory(categoryId, page, 10);
        model.addAttribute("productInCategory" , productInCategory);
        return "ddd/category/productList";
    }

    @RequestMapping("/ddd/product/{productId}")
    public String detail(@PathVariable("productId") String productId,
                         ModelMap model,
                         HttpServletResponse response) throws Exception {
        Optional<ProductData> product = productQueryService.getProduct(productId);
        if(product.isPresent()) {
            model.addAttribute("product", product.get());
            return "ddd/category/productDetail";
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }


}

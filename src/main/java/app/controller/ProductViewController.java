package app.controller;

import app.model.Product;
import app.service.ProductServise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class ProductViewController {

    private final ProductServise productServise;

    @GetMapping("/list")
    public String getAllProducts(Model model) {
        List<Product> products = productServise.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("pageTitle", "Список Продуктов");
        return "products/list";
    }

    @PostMapping("/addProduct")
    void addProduct(@RequestBody Product product) {
        productServise.addProduct(product);
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model) {
        Product products = productServise.getProduct(id);
        model.addAttribute("products", products);
        model.addAttribute("pageTitle", "Продукт: " + products.getName());
        return "products/edit";
    }

    @GetMapping("/product/category")
    public String findByCategory(Model model) {
        List<Product> byCategory = productServise.findByCategory();
        model.addAttribute("products", byCategory);
        model.addAttribute("pageTitle", "Продукты в наличии");
        return "products/category";
    }

    @GetMapping("/maxprice/{maxPrice}")
    public String findCheapProducts(@PathVariable("maxPrice") Double maxPrice, Model model) {
        List<Product> products = productServise.findCheapProducts(maxPrice);
        model.addAttribute("products", products);
        model.addAttribute("pageTitle", "Продукты дешевле"  +  maxPrice);
        return "products/maxPrice";
    }

    @DeleteMapping
    void deleteProduct(int id) {
        productServise.deleteProduct(id);
    }


}

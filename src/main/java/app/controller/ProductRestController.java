package app.controller;

import app.model.Product;
import app.service.ProductServise;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductServise productServise;

    @GetMapping
    List<Product> getAllProducts() {
       return productServise.getAllProducts();
    }

    @PostMapping
    void addProduct(Product product) {
        productServise.addProduct(product);
    }

    @GetMapping("/{id}")
    Product getProduct(@PathVariable("id") int id) {
        return productServise.getProduct(id);
    }

    @GetMapping("/category")
    List<Product> findByCategory() {
        return productServise.findByCategory();
    }

    @GetMapping("/{maxPrice}")
    List<Product> findCheapProducts(@PathVariable("maxPrice") double maxPrice) {
        return productServise.findCheapProducts(maxPrice);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable("id") int id) {
        productServise.deleteProduct(id);
    }

}

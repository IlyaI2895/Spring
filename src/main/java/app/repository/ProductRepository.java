package app.repository;

import app.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();

    void addProduct(Product product);

    Product getProduct(int id);

    List<Product> findByCategory();

    List<Product> findCheapProducts(double maxPrice);

    void deleteProduct(int id);


}

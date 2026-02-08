package service;

import model.Product;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.List;
@Service("productService")
public class ProductServiceImpl implements ProductServise {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts() ;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);

    }

    @Override
    public Product getProduct(int id) {
        return productRepository.getProduct(id);
    }

    @Override
    public List<Product> findByCategory() {
       return productRepository.findByCategory();
    }

    @Override
    public List<Product> findCheapProducts(double maxPrice) {
        return productRepository.findCheapProducts(maxPrice);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);

    }
}

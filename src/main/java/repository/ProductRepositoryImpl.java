package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.Data;
import lombok.Setter;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository("productRepository")
@Data
public class ProductRepositoryImpl implements ProductRepository {
    @Setter
    @Value("#{'${product.json}'}")
    private String data;

    @Setter
    @Value("curencyFormater")
    private CurencyFormater cf;

    @Override
    public void deleteProduct(int id) {
        List<Product> allProducts = getAllProducts();
        List<Product> collect = allProducts.stream()
                .filter(product -> product.getId() == id).collect(Collectors.toList());
        allProducts.remove(collect);
        rewriteData(allProducts);


    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return newMapper().readValue(new File(data),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            System.out.println("Отсутствие Json файла");
            System.out.println("Инициализация нового JSON файла...");
            rewriteData(initRepository());
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addProduct(Product product) {
        List<Product> allProducts = getAllProducts();
        int nextId;
        if (allProducts.isEmpty()) {
            nextId = 1;
        } else {
            nextId = allProducts.get(allProducts.size() - 1).getId() + 1;
        }
        product.setId(nextId);
        allProducts.add(product);
        rewriteData(allProducts);

    }

    @Override
    public Product getProduct(int id) {
        return getAllProducts().stream().filter(product -> product.getId() == id).findFirst().orElse(null);

    }


    @Override
    public List<Product> findByCategory() {
        boolean category = true;
        List<Product> prod = getAllProducts().stream()
                .filter(product -> category == product.isInStock()).collect(Collectors.toList());
        return prod;


    }

    @Override
    public List<Product> findCheapProducts(double maxPrice) throws RuntimeException {
        List<Product> products = getAllProducts().stream().filter(product -> maxPrice > product.getPrice())
                .collect(Collectors.toList());
        return products;

    }

    private void rewriteData(List<Product> products) {
        try {
            newMapper().writeValue(new File(this.data), products);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }

    private static List<Product> initRepository() {
        List<Product> products = TestDataGenerator.generateRandomBooks(50);
        List<Product> localLibrary = new ArrayList<>();
        ;
        IntStream.range(0, products.size())
                .forEach(index -> {
                    Product product1 = products.get(index);
                    product1.setId(index + 1);
                    localLibrary.add(product1);
                });
        return localLibrary;
    }
}

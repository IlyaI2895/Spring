package config;

import controller.MainController;
import controller.ProductController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import repository.CurencyFormater;
import repository.ProductRepository;
import repository.ProductRepositoryImpl;
import service.ProductServiceImpl;
import service.ProductServise;

@Configuration(enforceUniqueMethods = false, proxyBeanMethods = true)
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "java",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ProductRepository.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
        })

public class MySpringConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public CurencyFormater curencyFormater(@Value("${product.file}") String pattern) {
        return new CurencyFormater(pattern);
    }

    @Bean("productRepository")
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean("productService")
    public ProductServise productServise() {
        return new ProductServiceImpl(productRepository());
    }
    @Bean("productController")
    public ProductController productController(){
        return new ProductController(productServise());
    }
    @Bean("mainController")
    public MainController mainController(){
        return new MainController(productController());
    }
}





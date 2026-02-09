package app.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import app.repository.CurencyFormater;
import app.repository.ProductRepository;


@Configuration(enforceUniqueMethods = false, proxyBeanMethods = true)
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "app",
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
}





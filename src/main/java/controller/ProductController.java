package controller;

import model.Product;
import org.springframework.stereotype.Component;
import service.ProductServise;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Component
public class ProductController {
    private final ProductServise productServise;
    private Scanner scanner;

    public ProductController(ProductServise productServise) {
        this.productServise = productServise;
    }

    @PostConstruct
    public void init() {
        this.scanner = new Scanner(System.in);

    }

    @PreDestroy
    public void destroy() {
        this.scanner.close();
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> findByCategory();
                case 4 -> findProductById();
                case 5 -> findCheapProducts();
                case 6 -> getAllProduct();
                case 0 -> {
                    running = false;
                    destroy();
                    System.out.println("Выход из программы...");

                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }

            if (running) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== МЕНЮ МАГАЗИНА ===");
        System.out.println("1. Добавить продукт");
        System.out.println("2. Удалить продукт");
        System.out.println("3. Найти продукты в наличии");
        System.out.println("4. Найти товар по Id");
        System.out.println("5. Показать продукты по цене ниже 500 BYN");
        System.out.println("6. Показать все продукты");
        System.out.println("0. Выход");
    }

    private void addProduct() {
        System.out.println("\n=== ДОБАВЛЕНИЕ ПРОДУКТА ===");

        int id = getIntInput("ID продукта: ");
        String name = getStringInput("Название: ");
        double price = getDoubleInput("Цена: ");
        int firstName = getIntInput("Товар в наличии?: ");
        System.out.println("1. Да");
        System.out.println("2. Нет");

        if (firstName == 1) {
            Product product = new Product(id, name, true, price);
            productServise.addProduct(product);

        }
        if (firstName == 2) {
            Product product = new Product(id, name, false, price);
            productServise.addProduct(product);
        }
    }

    private void removeProduct() {
        System.out.println("\n=== УДАЛЕНИЕ ПРОДУКТА ===");
        int id = getIntInput("Введите ID продукта для удаления: ");
        productServise.deleteProduct(id);
        System.out.println("Пролукт удален (если существовал)");
    }

    private void findByCategory() {
        System.out.println("\n=== ПРОДУКТЫ В НАЛИЧИИ ===");
        List<Product> products = productServise.findByCategory();
        displayProducts(products);
    }

    private void getAllProduct() {
        System.out.println("\n=== ВСЕ ПРОДУКТЫ ===");
        List<Product> allProducts = productServise.getAllProducts();
        displayProducts(allProducts);
    }

    private void findProductById() {
        System.out.println("\n=== ПОИСК ПО ID ===");
        int id = getIntInput("Введите ID продукта: ");
        Product product = productServise.getProduct(id);

        if (product != null) {
            System.out.println("Найден продукт:");
            System.out.println(product);
        } else {
            System.out.println("Продукт с ID " + id + " не найден");
        }
    }

    private void findCheapProducts() {
        System.out.println("\n=== ПРОДУКТЫ ПО ЦЕНЕ НИЖЕ 500 BYN ===");
        List<Product> product = productServise.findCheapProducts(500);
        if (product != null) {
            System.out.println("Найден(о) продукт(ов):" + product.size());
            System.out.println(product);
        } else {
            System.out.println("Продуктов с такой ценой не найдено");
        }
    }

    private void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Продукт(ы) не найден(ы)");
            return;
        }

        System.out.println("Найдено продуктов: " + products.size());
        IntStream.range(0, products.size())
                .forEach(i -> System.out.printf("%d. %s\n", i + 1, products.get(i)));
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите целое число");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число");
            }
        }
    }


}

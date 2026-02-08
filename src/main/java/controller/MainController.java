package controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainController {


    final ProductController productController;

    @Autowired
    public MainController(ProductController productController) {
        this.productController = productController;

    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("Напишите p или product для начала:");
            System.out.println("0, или exit чтобы выйти");
            Scanner scanner = new Scanner(System.in);
            String trim = scanner.nextLine().trim();

            switch (trim) {
                case "p", "product" -> productController.start();
                case "0", "exit" -> running = false;
            }

        }
    }
}

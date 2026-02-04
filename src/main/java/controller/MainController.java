package controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;


public class MainController {

    final ProductController productController;

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
                case "p", "product" ->  productController.start();
                case "0", "exit" -> running = false;
            }

        }
    }
}

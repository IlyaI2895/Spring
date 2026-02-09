package app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MainController {

    private final ProductController productController;

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

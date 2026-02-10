package app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

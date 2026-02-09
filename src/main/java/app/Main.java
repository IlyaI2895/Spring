package app;

import app.config.MySpringConfig;
import app.controller.MainController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MySpringConfig.class);
        context.refresh();
        MainController controller = context.getBean(MainController.class);
            controller.start();




    }
}

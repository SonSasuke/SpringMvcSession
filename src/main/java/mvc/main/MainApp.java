package mvc.main;

import mvc.configuration.JPAConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JPAConfig.class);

    public static void main(String[] args) {

    }
}

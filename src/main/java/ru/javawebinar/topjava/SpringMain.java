package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;

import java.util.Arrays;

/**
 * @author Timur Muratov
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(context.getBeanDefinitionNames()) + "\n");
        InMemoryUserRepositoryImpl inMemoryUserRepositoryImpl = (InMemoryUserRepositoryImpl)context.getBean("inMemoryUserRepositoryImpl");
        inMemoryUserRepositoryImpl = context.getBean(InMemoryUserRepositoryImpl.class);
        context.close();
    }
}

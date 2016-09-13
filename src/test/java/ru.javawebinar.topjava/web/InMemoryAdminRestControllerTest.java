package ru.javawebinar.topjava.web;

import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

/**
 * @author Timur Muratov
 */
public class InMemoryAdminRestControllerTest {
    private static ConfigurableApplicationContext context;
    private static AdminRestController controller;

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(context.getBeanDefinitionNames()) + "\n");
        controller = context.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        context.close();
    }

    @Before
    public void setUp() throws Exception {
        UserRepository repository = context.getBean(UserRepository.class);
        repository.getAll().forEach(user -> repository.delete(user.getId()));
        repository.save(ADMIN);
        repository.save(USER);
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }
}

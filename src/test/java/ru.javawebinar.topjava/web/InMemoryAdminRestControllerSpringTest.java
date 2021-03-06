package ru.javawebinar.topjava.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.jdbc.JdbcUserRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Collection;

import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

/**
 * @author Timur Muratov
 */
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    AdminRestController controller;

    @Autowired
    UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.getAll().forEach(user -> repository.delete(user.getId()));
        repository.save(ADMIN);
        repository.save(USER);
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(100001);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }
}

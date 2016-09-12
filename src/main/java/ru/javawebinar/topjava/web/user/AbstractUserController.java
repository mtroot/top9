package ru.javawebinar.topjava.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;

import java.util.List;

/**
 * @author Timur Muratov
 */
public abstract class AbstractUserController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractUserController.class);

    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("Get user with id = {}", id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        LOG.info("Create {}", user);
        return service.save(user);
    }

    public void delete(int id) {
        LOG.info("Delete user with id = {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        user.setId(id);
        LOG.info("Update {}", user);
        service.update(user);
    }

    public User getByEmail(String email) {
        LOG.info("Get user with id = {}", email);
        return service.getByEmail(email);
    }
}

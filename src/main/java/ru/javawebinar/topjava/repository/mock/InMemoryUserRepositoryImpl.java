package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Timur Muratov
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Comparator<User> userComparator = (o1, o2) -> o1.getName().compareTo(o2.getName());
    private AtomicInteger currentId = new AtomicInteger(0);
    private Map<Integer, User> users = new ConcurrentHashMap<>();

    {
        save(new User(null, "Admin", "admin@mail.ru", "password", Role.ROLE_ADMIN));
        save(new User(null, "User", "user@mail.ru", "password", Role.ROLE_USER));
    }

    @Override
    public User save(User user) {
        LOG.info("save");
        if (user.isNew()) {
            user.setId(currentId.getAndIncrement());
        }
        return users.put(user.getId(), user);
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete");
        return users.remove(id) != null;
    }

    @Override
    public User get(int id) {
        LOG.info("get");
        return users.get(id);
    }

    @Override
    public User getByEmail(String email) {
        Objects.requireNonNull(email);
        LOG.info("getByEmail");
        return users.values().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return users.values().stream().sorted(userComparator).collect(Collectors.toList());
    }
}

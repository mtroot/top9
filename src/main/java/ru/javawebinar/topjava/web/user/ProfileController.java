package ru.javawebinar.topjava.web.user;

import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;

/**
 * @author Timur Muratov
 */
public class ProfileController extends AbstractUserController {
    @Override
    public User get(int id) {
        return super.get(LoggedUser.id());
    }

    @Override
    public void delete(int id) {
        super.delete(LoggedUser.id());
    }

    public void update(User user) {
        super.update(user, LoggedUser.id());
    }
}

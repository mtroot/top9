package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

/**
 * @author Timur Muratov
 */
public class UserTestData {
    public static final int ADMIN_ID = 0;
    public static final int USER_ID = 1;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@mail.ru", "password", Role.ROLE_ADMIN);
    public static final User USER = new User(USER_ID, "User", "user@mail.ru", "12345", Role.ROLE_USER);
}

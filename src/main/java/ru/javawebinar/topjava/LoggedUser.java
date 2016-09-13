package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.MealsUtil;

/**
 * @author Timur Muratov
 */
public class LoggedUser {
    private static int id;

    public static void setId(int userId) {
        id = userId;
    }

    public static int id() {
        return id;
    }

    public static int getCaloriesPerDay(){
        return MealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}

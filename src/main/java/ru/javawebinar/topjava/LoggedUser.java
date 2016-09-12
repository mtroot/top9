package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.MealsUtil;

/**
 * @author Timur Muratov
 */
public class LoggedUser {
    public static int id() {
        return 1;
    }

    public static int getCaloriesPerDay(){
        return MealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}

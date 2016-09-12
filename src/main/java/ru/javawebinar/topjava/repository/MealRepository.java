package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface CRUD for Meal.class
 * @author Timur Muratov
 */
public interface MealRepository {

    Meal save(Meal meal, int userId);

    Meal get(int id, int userId);

    boolean delete(int id, int userId);

    List<Meal> getAll(int userId);

    List<Meal> getBetweenDate(int userId, LocalDate start, LocalDate end);
}

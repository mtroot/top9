package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Interface CRUD for Meal.class
 * @author Timur Muratov
 */
public interface MealRepository {

    Meal save(Meal meal);

    Meal getById(int id);

    boolean delete(int id);

    List<Meal> getAll();
}

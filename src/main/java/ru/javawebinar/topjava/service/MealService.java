package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * @author Timur Muratov
 */
public interface MealService {
    Meal save(Meal meal);

    Meal get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Meal> getAll();

    void update(Meal meal);
}

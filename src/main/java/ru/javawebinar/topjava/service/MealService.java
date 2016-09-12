package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Timur Muratov
 */
public interface MealService {
    Meal save(Meal meal, int userId);

    Meal get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    List<Meal> getAll(int userId);

    void update(Meal meal, int userId);

    List<Meal> getBetweenDate(int userId, LocalDate startDate, LocalDate endDate);
}

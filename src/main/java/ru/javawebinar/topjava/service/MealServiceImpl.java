package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Timur Muratov
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return ExceptionUtil.checkedNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ExceptionUtil.checkedNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(Meal meal, int userId) {
        repository.save(meal, userId);
    }

    @Override
    public List<Meal> getBetweenDate(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenDate(userId, startDate, endDate);
    }
}

package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * @author Timur Muratov
 */
public class MealServiceImpl implements MealService {

    MealRepository repository;
    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return ExceptionUtil.checkedNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkedNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);
    }
}

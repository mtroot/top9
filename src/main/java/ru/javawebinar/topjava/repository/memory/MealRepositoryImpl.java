package ru.javawebinar.topjava.repository.memory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Timur Muratov
 */
public class MealRepositoryImpl implements MealRepository {
    private AtomicInteger currentId = new AtomicInteger(0);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();

    public MealRepositoryImpl() {}

    public MealRepositoryImpl(List<Meal> mealList) {
        for (Meal meal : mealList) {
            save(meal);
        }
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()){
        meal.setId(currentId.getAndIncrement());
        }
        return repository.put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public List<Meal> getAll() {
        return repository.values().stream().collect(Collectors.toList());
    }
}

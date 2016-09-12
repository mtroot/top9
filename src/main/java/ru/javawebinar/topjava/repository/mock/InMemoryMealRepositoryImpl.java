package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Timur Muratov
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private AtomicInteger currentId = new AtomicInteger(0);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Comparator<Meal> mealComparator = (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime());
    {
        save(new Meal(null, 0, LocalDateTime.of(2016, Month.SEPTEMBER, 12, 10, 0), "Завтрак админа", 1000), 0);
        save(new Meal(null, 0, LocalDateTime.of(2016, Month.SEPTEMBER, 12, 13, 0), "Обед админа", 1000), 0);
        save(new Meal(null, 0, LocalDateTime.of(2016, Month.SEPTEMBER, 12, 17, 0), "Ужин админа", 1000), 0);

        for (Meal meal : MealsUtil.meals) {
            save(meal, 1);
        }
    }


    @Override
    public Meal save(Meal meal, int userId) {
        LOG.info("save");
        if (meal.isNew()){
        meal.setId(currentId.getAndIncrement());
        }
        meal.setUserId(userId);
        return repository.put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id, int userId) {
        LOG.info("get");
        Meal meal = repository.get(id);
        if (meal.getUserId() == userId) {
            return meal;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("delete");
        Meal meal = repository.get(id);
        if (meal == null || meal.getUserId() != userId) {
            return false;
        }
        return repository.remove(id) != null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        LOG.info("getAll");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(mealComparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetweenDate(int userId, LocalDate start, LocalDate end) {
        LOG.info("getBetweenDate");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId && TimeUtil.isBetweenDate(meal.getDate(), start, end))
                .sorted(mealComparator)
                .collect(Collectors.toList());
    }
}

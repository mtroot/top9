package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Timur Muratov
 */
@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public Meal save(Meal meal) throws NotFoundException{
        return service.save(meal, LoggedUser.id());
    }

    public Meal get(int id) throws NotFoundException{
        return service.get(id, LoggedUser.id());
    }

    public void delete(int id) throws NotFoundException {
        service.delete(id, LoggedUser.id());
    }

    public List<MealWithExceed> getAll() {
        return MealsUtil.getFilteredWithExceeded(service.getAll(LoggedUser.id()), LocalTime.MIN, LocalTime.MAX, LoggedUser.getCaloriesPerDay());
    }

    public void update(Meal meal) throws NotFoundException{
        service.get(meal.getId(), LoggedUser.id());
        service.update(meal, LoggedUser.id());
    }

    public List<MealWithExceed> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getFilteredWithExceeded(service.getBetweenDate(LoggedUser.id(), startDate, endDate), startTime, endTime, LoggedUser.getCaloriesPerDay());
    }
}

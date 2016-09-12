package ru.javawebinar.topjava.web;

import com.sun.deploy.net.HttpRequest;
import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.memory.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Timur Muratov
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private MealRepository repository;

    @Override
    public void init() throws ServletException {
        repository = new MealRepositoryImpl(MealsUtil.meals);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(req.getParameter("datetime")),
                req.getParameter("description"),
                Integer.valueOf(req.getParameter("calories")));
        repository.save(meal);
        resp.sendRedirect("meals");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            LOG.debug("getAll()");
            List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(repository.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
            req.setAttribute("ml", meals);
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            Integer id = getId(req);
            repository.delete(id);
            resp.sendRedirect("meals");
        } else {
            final Meal meal = action.equals("create") ?
                    new Meal(null, LocalDateTime.now(), "", 1000) :
                    repository.getById(getId(req));
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
        }
    }

    private Integer getId(HttpServletRequest request) {
        String parameterId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(parameterId);
    }
}

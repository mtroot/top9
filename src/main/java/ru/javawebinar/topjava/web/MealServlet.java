package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.sum;
import static java.lang.Integer.valueOf;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Timur Muratov
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private MealRepository repository;
    private static ConfigurableApplicationContext context;
    private MealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryMealRepositoryImpl();
        context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controller = context.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        context.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : valueOf(id),
                null,
                LocalDateTime.parse(req.getParameter("datetime")),
                req.getParameter("description"),
                valueOf(req.getParameter("calories")));
        controller.save(meal);
        resp.sendRedirect("meals");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            LOG.debug("getAll()");
            String startDate = req.getParameter("startDate");
            String startTime = req.getParameter("startTime");
            String endDate = req.getParameter("endDate");
            String endTime = req.getParameter("endTime");
            List<MealWithExceed> meals = controller.getAllFiltered(
                    !(startDate == null || startDate.isEmpty()) ? LocalDate.parse(startDate) : LocalDate.of(2015, 1, 1),
                    !(startTime == null || startTime.isEmpty()) ? LocalTime.parse(startTime) : LocalTime.MIN,
                    !(endDate == null || endDate.isEmpty()) ? LocalDate.parse(endDate) : LocalDate.of(2040, 1, 1),
                    !(endTime == null || endTime.isEmpty()) ? LocalTime.parse(endTime) : LocalTime.MAX);
            req.setAttribute("ml", meals);
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            Integer id = getId(req);
            controller.delete(id);
            resp.sendRedirect("meals");
        } else {
            final Meal meal = action.equals("create") ?
                    new Meal(null, null, LocalDateTime.now(), "", 1000) :
                    controller.get(getId(req));
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
        }
    }

    private Integer getId(HttpServletRequest request) {
        String parameterId = Objects.requireNonNull(request.getParameter("id"));
        return valueOf(parameterId);
    }
}

package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private final MealRepository mealRepository = new InMemoryMealRepositoryImpl();
    private static final int CALORIES_PER_DAY = 10000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (Objects.isNull(action)) {
            showMeals(request, response);
            return;
        }
        String mealId = request.getParameter("id");

        Integer id = Objects.nonNull(mealId) && mealId.length() > 0 ? Integer.parseInt(mealId) : null;
        switch (action) {
            case "edit":
                Meal meal = mealRepository.getById(id);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("edit.jsp").forward(request, response);
                break;
            case "delete":
                mealRepository.delete(Integer.parseInt(mealId));
                showMeals(request, response);
        }
    }

    private void showMeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> meals = MealsUtil.filteredByStreams(mealRepository.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        Meal meal = MealsUtil.mapToEntity(parameterMap);
        mealRepository.save(meal);
        showMeals(req, resp);
    }
}
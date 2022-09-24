package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/meals")
public class JspMealServlet extends AbstractMealController {
    private final Logger log = LoggerFactory.getLogger(JspMealServlet.class);


    public JspMealServlet(MealService mealService) {
        super(mealService);
    }

    @PostMapping
    public ModelAndView createMeal(Meal meal, HttpServletRequest request) {
        int userId = SecurityUtil.authUserId();
        if (meal.isNew()) {
            create(meal);
        } else {
            update(meal, meal.getId());
        }
        return new ModelAndView("redirect:/meals");
    }

    @GetMapping
    public ModelAndView getAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                               @RequestParam(required = false) LocalTime startTime,
                               @RequestParam(required = false) LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("Get all meals for user {}.", userId);
        log.info("{}{}{}{}", startDate, endDate, startTime, endTime);
        List<MealTo> mealTos = getBetween(startDate, startTime, endDate, endTime);
        ModelAndView modelAndView = new ModelAndView("meals");
        modelAndView.addObject("meals", mealTos);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteMeal(@RequestParam Integer id) {
        delete(id);
        return new ModelAndView("redirect:/meals");
    }

    @PostMapping("/edit")
    public ModelAndView createOrUpdateMeal(@RequestParam(required = false) Integer id, HttpServletRequest request) {
        Meal meal;
        if (Objects.nonNull(id)) {
            meal = get(id);
        } else {
            meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        }
        ModelAndView modelAndView = new ModelAndView("mealForm");
        modelAndView.addObject("meal", meal);
        return modelAndView;
    }
}
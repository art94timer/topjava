package ru.javawebinar.topjava.web.meal;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

@Component
@RequiredArgsConstructor
public class MealRestController {

	private final MealService service;

	public List<MealTo> getAll() {
		int userId = SecurityUtil.authUserId();
		Collection<Meal> meals = service.getAll(userId);
		return MealsUtil.getTos(meals, SecurityUtil.authUserCaloriesPerDay());
	}

	public Meal get(int id) {
		int userId = SecurityUtil.authUserId();
		return service.get(id, userId);
	}

	public Meal save(Meal meal) {
		int userId = SecurityUtil.authUserId();
		return service.save(meal, userId);
	}

	public void delete(int id) {
		int userId = SecurityUtil.authUserId();
		service.delete(id, userId);
	}
}
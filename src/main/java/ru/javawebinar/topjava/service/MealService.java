package ru.javawebinar.topjava.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    // null if updated meal does not belong to userId
    public Meal save(Meal meal, int userId) {
        if (userId != meal.getUserId()) {
            throw new UnsupportedOperationException("Current user hasn't permission to this meal.");
        }
        return repository.save(meal);
    }

    // false if meal does not belong to userId
    public boolean delete(int id, int userId) {
        Meal meal = repository.get(id);
        if (Objects.isNull(meal) || userId != meal.getUserId()) {
            return false;
        }
        return repository.delete(id);
    }

    // null if meal does not belong to userId
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        if (Objects.isNull(meal) || userId != meal.getUserId()) {
            throw new NotFoundException(String.format("Meal id %d, userId %d", id, userId));
        }
        return meal;
    }

    // ORDERED dateTime desc
    public Collection<Meal> getAll(int userId) {
        return repository.getAll().stream()
                .filter(meal -> userId == meal.getUserId())
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }
}
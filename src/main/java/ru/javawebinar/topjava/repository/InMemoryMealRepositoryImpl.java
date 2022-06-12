package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepositoryImpl implements MealRepository {

    private static final Map<Integer, Meal> data = new ConcurrentHashMap<>();
    private static final AtomicInteger SEQUENCE = new AtomicInteger();

    static {
        int nextId = SEQUENCE.incrementAndGet();
        data.put(nextId, new Meal(nextId, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        nextId = SEQUENCE.incrementAndGet();
        data.put(nextId, new Meal(nextId, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        nextId = SEQUENCE.incrementAndGet();
        data.put(nextId, new Meal(nextId, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        nextId = SEQUENCE.incrementAndGet();
        data.put(nextId, new Meal(nextId, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        nextId = SEQUENCE.incrementAndGet();
        data.put(nextId, new Meal(nextId, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        nextId = SEQUENCE.incrementAndGet();
        data.put(nextId, new Meal(nextId, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        nextId = SEQUENCE.incrementAndGet();
        data.put(nextId, new Meal(nextId, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal save(Meal meal) {
        Integer id = meal.getId();
        if (Objects.isNull(id)) {
            id = SEQUENCE.incrementAndGet();
            meal.setId(id);
        }
        return data.put(id, meal);
    }

    @Override
    public Meal delete(Integer id) {
        return data.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Meal getById(Integer id) {
        return data.get(id);
    }
}
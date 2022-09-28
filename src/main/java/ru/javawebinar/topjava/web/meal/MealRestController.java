package ru.javawebinar.topjava.web.meal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RestController
@RequestMapping("/rest/meals")
public class MealRestController extends AbstractMealController {

    static final String REST_URL = "/meals";

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        super.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empty")
    public ResponseEntity<Meal> getEmpty() {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000);
        return ResponseEntity.ok(meal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> get(@PathVariable Integer id) {
        Meal meal = super.get(id);
        return ResponseEntity.ok(meal);
    }

    @PutMapping
    public ResponseEntity<Meal> updateOrCreate(@RequestBody Meal meal) {
        if (meal.isNew()) {
            Meal created = super.create(meal);
            URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(created);
        }
        super.update(meal, meal.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MealTo>> getBetweenDateTime(@RequestParam(required = false) LocalDate startDate,
                                                    @RequestParam(required = false) LocalDate endDate,
                                                    @RequestParam(required = false) LocalTime startTime,
                                                    @RequestParam(required = false) LocalTime endTime) {
        List<MealTo> mealTos = super.getBetween(startDate, startTime, endDate, endTime);
        return ResponseEntity.ok(mealTos);
    }
}
package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

   @Query("FROM Meal m WHERE m.user.id = ?1 " +
           "AND m.dateTime >= ?2 " +
           "AND m.dateTime < ?3 " +
           "ORDER BY m.dateTime DESC")
   List<Meal> getByUserIdAndDateTimeInRange(Integer userId, LocalDateTime startTime, LocalDateTime endTime);
      //List<Meal> getByUserIdAndDateTimeGreaterThanEqualAndDateTimeLessThanOrderByDateTimeDesc(Integer userId, LocalDateTime startTime, LocalDateTime endTime);

   List<Meal> getByUserIdOrderByDateTimeDesc(Integer userId);
   int deleteByIdAndUserId(Integer id, Integer userId);

   Meal getByIdAndUserId(Integer id, Integer userId);
}

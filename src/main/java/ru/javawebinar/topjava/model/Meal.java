package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@NamedQueries(value = {
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal where id = :id and user.id = :userId"),
        @NamedQuery(name = Meal.GET_BETWEEN_DATETIME, query = "SELECT m FROM Meal m where m.dateTime > ?1 and m.dateTime < ?2 and m.user.id = ?3 ORDER BY m.dateTime desc"),
        @NamedQuery(name = Meal.BY_USER_AND_ID, query = "SELECT m FROM Meal m where m.user.id =:userId and m.id = :id ORDER BY m.dateTime desc"),
        @NamedQuery(name = Meal.BY_USER, query = "SELECT m FROM Meal m where m.user.id =:userId ORDER BY m.dateTime desc")
})
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {


    public static final String DELETE = "Meal.delete";
    public static final String BY_USER_AND_ID = "Meal.getByUserAndId";
    public static final String BY_USER = "Meal.getByUser";
    public static final String GET_BETWEEN_DATETIME = "Meal.getBetweenDatetime";
    public static final String UPDATE = "Meal.update";

    @NotNull
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @NotNull
    private String description;
    @NotNull
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}

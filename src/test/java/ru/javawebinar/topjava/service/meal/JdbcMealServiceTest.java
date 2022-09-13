package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles(inheritProfiles = true, value = "jdbc")
public class JdbcMealServiceTest extends MealServiceTest {

}
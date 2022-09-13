package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles(inheritProfiles = true, value = "jpa")
public class JpaMealServiceTest extends MealServiceTest {

}
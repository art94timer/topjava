package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(inheritProfiles = true, value = "datajpa")
public class DataJpaMealServiceTest extends MealServiceTest {

}
package ru.javawebinar.topjava.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml" })
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

	@Autowired
	private MealService service;

	@Test
	public void testGetMeal() {
		Integer adminMealId = MealTestData.lunch_admin_meal.getId();
		Meal meal = service.get(adminMealId, UserTestData.ADMIN_ID);
		assertEquals(adminMealId, meal.getId());
	}

	@Test
	public void testGetAnotherPersonMeal() {
		Integer adminMealId = MealTestData.lunch_admin_meal.getId();
		assertThrows(NotFoundException.class, () -> service.get(adminMealId, UserTestData.USER_ID));
	}

	@Test
	public void testDeleteMeal() {
		Integer adminMealId = MealTestData.lunch_admin_meal.getId();
		assertNotNull(service.get(adminMealId, UserTestData.ADMIN_ID));
		service.delete(adminMealId, UserTestData.ADMIN_ID);
		assertThrows(NotFoundException.class, () -> service.delete(adminMealId, UserTestData.USER_ID));
	}

	@Test
	public void testDeleteAnotherPersonMeal() {
		Integer adminMealId = MealTestData.lunch_admin_meal.getId();
		assertThrows(NotFoundException.class, () -> service.delete(adminMealId, UserTestData.USER_ID));
	}

	@Test
	public void testSaveDupDateTime() {
	  Meal meal = MealTestData.lunch_admin_meal;
	  Meal anotherMealWithSameDateTime = new Meal(meal);
	  anotherMealWithSameDateTime.setId(null);
	  assertThrows(DuplicateKeyException.class, () -> service.create(anotherMealWithSameDateTime, UserTestData.ADMIN_ID)); 
	  
	}

}

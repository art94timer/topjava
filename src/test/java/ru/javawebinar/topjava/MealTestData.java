package ru.javawebinar.topjava;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

public class MealTestData {
	public static final int START_SEQ = 100000;
	public static final int USERMEAL_ID = START_SEQ;
	public static final int ADMINMEAL_ID = START_SEQ + 1;
	public static final int GUESTMEAL_ID = START_SEQ + 2;

	public static final int NOT_FOUND = 10;

	public static final Meal breakfast_user_meal = new Meal(USERMEAL_ID, LocalDate.of(2022, 1, 1).atStartOfDay(),
			"Breakfast",
			UserTestData.USER_ID);
	public static final Meal lunch_admin_meal = new Meal(ADMINMEAL_ID, LocalDate.of(2022, 1, 1).atStartOfDay(), "Lunch",
			UserTestData.ADMIN_ID);
	public static final Meal dinner_guest_meal = new Meal(GUESTMEAL_ID, LocalDate.of(2022, 1, 1).atStartOfDay(),
			"Dinner",
			UserTestData.GUEST_ID);

	public static Meal getNew() {
		return new Meal(LocalDateTime.now(), "New", 100);
	}

	public static Meal getUpdated() {
		Meal meal = new Meal(breakfast_user_meal);
		return meal;
	}

	public static void assertMatch(User actual, User expected) {
		assertThat(actual).usingRecursiveComparison().ignoringFields("registered", "roles").isEqualTo(expected);
	}

	public static void assertMatch(Iterable<User> actual, User... expected) {
		assertMatch(actual, Arrays.asList(expected));
	}

	public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
		assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("registered", "roles")
				.isEqualTo(expected);
	}
}

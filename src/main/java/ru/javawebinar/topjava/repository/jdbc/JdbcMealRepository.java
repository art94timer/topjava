package ru.javawebinar.topjava.repository.jdbc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealRowMapper;

@Repository
public class JdbcMealRepository implements MealRepository {

	private static final String SELECT_BETWEEN_DATE_TIME_AND_USERID = "SELECT * FROM meals WHERE user_id = :user_id AND date_time BETWEEN :startDateTime AND :endDateTime ORDER BY date_time DESC";
	private static final String SELECT_BY_USERID = "SELECT * FROM meals WHERE user_id = :user_id";
	private static final String SELECT_SQL_BY_ID_AND_USERID = "SELECT * FROM meals WHERE id = :id AND user_id = :user_id ORDER BY date_time DESC";
	private static final String DELETE_SQL = "DELETE FROM meals WHERE id = :id AND user_id = :user_id";
	private static final String UPDATE_SQL = "UPDATE meals SET date_time = :date_time, description = :description, calories = :calories WHERE id =:id AND user_id = :user_id";
	private final SimpleJdbcInsert insertMeal;

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcMealRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.jdbcTemplate = namedJdbcTemplate;
		this.insertMeal = new SimpleJdbcInsert(
				namedJdbcTemplate.getJdbcTemplate()).withTableName("meals")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public Meal save(Meal meal, int userId) {
		if (meal.isNew()) {
			Number newIdNumber = insertMeal
					.executeAndReturnKey(fillMealNamedParams(meal, userId));
			meal.setId(newIdNumber.intValue());
		} else {
			jdbcTemplate.update(UPDATE_SQL, fillMealNamedParams(meal, userId));
		}

		return meal;
	}

	@Override
	public boolean delete(int id, int userId) {
		int updatedRows = jdbcTemplate.update(DELETE_SQL,
				Map.of("id", id, "user_id", userId));
		return updatedRows != 0;
	}

	@Override
	public Meal get(int id, int userId) {
		List<Meal> result = jdbcTemplate.query(SELECT_SQL_BY_ID_AND_USERID,
				Map.of("id", id, "user_id", userId), new MealRowMapper());
		if (result.size() > 1) {
			throw new RuntimeException("Non unique result");
		}
		return CollectionUtils.isEmpty(result) ? null : result.get(0);
	}

	@Override
	public List<Meal> getAll(int userId) {
		return jdbcTemplate.query(SELECT_BY_USERID, Map.of("user_id", userId),
				new MealRowMapper());
	}

	@Override
	public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime,
			LocalDateTime endDateTime, int userId) {
		return jdbcTemplate
				.query(SELECT_BETWEEN_DATE_TIME_AND_USERID,
						Map.of("user_id", userId, "startDateTime",
								startDateTime, "endDateTime", endDateTime),
						new MealRowMapper());
	}

	private Map<String, Object> fillMealNamedParams(Meal meal, int userId) {
		Map<String, Object> namedParamsMap = new HashMap<>();
		namedParamsMap.put("id", meal.getId());
		namedParamsMap.put("date_time", meal.getDateTime());
		namedParamsMap.put("description", meal.getDescription());
		namedParamsMap.put("calories", meal.getCalories());
		namedParamsMap.put("user_id", userId);
		return namedParamsMap;
	}
}

package ru.javawebinar.topjava.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.jdbc.core.RowMapper;

import ru.javawebinar.topjava.model.Meal;

public class MealRowMapper implements RowMapper<Meal> {

	@Override
	public Meal mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("id");
		Timestamp dateTime = rs.getTimestamp("date_time");
		String desc = rs.getString("description");
		int calories = rs.getInt("calories");
		LocalDateTime localDateTime = Instant.ofEpochMilli(dateTime.getTime()).atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		Meal meal = new Meal(id, localDateTime, desc, calories);
		return meal;
	}
}

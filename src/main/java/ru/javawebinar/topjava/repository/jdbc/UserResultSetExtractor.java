package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.util.StringUtils;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<User> users = new ArrayList<>();
        User currentUser = null;
        while (rs.next()) {
            int id = rs.getInt("id");

            if (Objects.isNull(currentUser)) {
                currentUser = mapUser(rs, id);
            } else if (!Objects.equals(currentUser.getId(), id)) {
                users.add(currentUser);
                currentUser = mapUser(rs, id);
            }

            String roleVal = rs.getString("role");
            if (StringUtils.hasText(roleVal)) {
                Role role = Role.valueOf(roleVal);
                currentUser.getRoles().add(role);
            }
        }

        if (currentUser != null) {
            users.add(currentUser);
        }
        return users;
    }

    private User mapUser(ResultSet rs, int id) throws SQLException {
        User user = new User();
        user.setId(id);
        user.setEmail(rs.getString("email"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setPassword(rs.getString("password"));
        user.setRegistered(rs.getTimestamp("registered"));
        user.setCaloriesPerDay(rs.getInt("calories_per_day"));
        user.setName(rs.getString("name"));
        ArrayList<Role> roles = new ArrayList<>();
        user.setRoles(roles);
        return user;
    }
}
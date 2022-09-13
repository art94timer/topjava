package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.function.Function;

@Configuration
public class DateTimeResolver {

    @Bean
    @Profile("hsqldb")
    public Function<LocalDateTime, Timestamp> getTimestamp() {
        return Timestamp::valueOf;
    }

    @Bean
    @Profile("postgres")
    public Function<LocalDateTime, LocalDateTime> getLocalDateTime() {
        return dt -> dt;
    }
}

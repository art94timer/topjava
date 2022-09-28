package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.TEST_MEAL_URI;

class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/rest/meals";

    @Test
    void deleteMeal() throws Exception {
        perform(delete(TEST_MEAL_URI))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void getMeal() throws Exception {
        perform(get(TEST_MEAL_URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(MealTestData.meal1));
    }

    @Test
    void getEmpty() throws Exception {
        perform(get(REST_URL + "/empty"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(MealTestData.emptyMeal));
    }

    @Test
    void updateOrCreateMeal() throws Exception {
        Meal updated = MealTestData.getUpdated();
        perform(put(REST_URL)
                .content(JsonUtil.writeValue(updated))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        Meal newMeal = MealTestData.getNew();
        perform(put(REST_URL)
                .content(JsonUtil.writeValue(newMeal)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getBetweenMeal() throws Exception {
        String json = JsonUtil.writeValue(MealTestData.meals);
        perform(get(REST_URL)
                .queryParam("startDateTime", LocalDateTime.of(2000, 12, 31, 0, 0).toString())
                .queryParam("endDateTime", LocalDateTime.of(2022, 1, 1, 23, 59).toString()))
                .andDo(print())
                .andExpect(content().json(json, false))
                .andExpect(status().isOk());
    }
}
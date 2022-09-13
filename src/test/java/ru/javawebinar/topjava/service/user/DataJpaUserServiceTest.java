package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(inheritProfiles = true, value = "datajpa")
public class DataJpaUserServiceTest extends UserServiceTest {
}

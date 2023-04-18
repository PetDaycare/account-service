package com.userservice.rest;
import com.userservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
class AccountResourceTest {

    @Mock
    AccountService accountService;
    @InjectMocks
    AccountResource accountResource = new AccountResource();


    @Test
    void registerUser() {

        assertTrue(true);
    }

    @Test
    void confirmUser() {

        assertTrue(true);
    }

    @Test
    void loginUser() {

        assertTrue(true);
    }
}
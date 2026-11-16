package edu.westga.comp4420.grocery_list.test.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.grocery_list.model.LoginService;

class TestLoginService {

    @Test
    void testValidCredentials() {
        LoginService service = new LoginService();

        assertTrue(service.authenticate("user", "Money123!"));
    }

    @Test
    void testIncorrectUsername() {
        LoginService service = new LoginService();

        assertFalse(service.authenticate("wrong", "Money123!"));
    }

    @Test
    void testIncorrectPassword() {
        LoginService service = new LoginService();

        assertFalse(service.authenticate("user", "wrong"));
    }

    @Test
    void testMissingCredentials() {
        LoginService service = new LoginService();

        assertFalse(service.authenticate(null, null));
        assertFalse(service.authenticate("user", null));
        assertFalse(service.authenticate("", ""));
    }
}
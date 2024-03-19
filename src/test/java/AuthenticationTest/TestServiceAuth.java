package AuthenticationTest;

import org.junit.jupiter.api.Test;

import Authentication.ServiceAuth;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceAuth {

    @Test
    public void testInvalidEmail() {
    	ServiceAuth authService = new ServiceAuth();
        String result = authService.authenticateUser("invalid@example.com", "password123");
        assertEquals("Invalid email", result);
    }

    @Test
    public void testWrongPassword() {
    	ServiceAuth authService = new ServiceAuth();
        String result = authService.authenticateUser("example@example.com", "wrongpassword");
        assertEquals("Wrong password", result);
    }

    @Test
    public void testSuccessfulLogin() {
    	ServiceAuth authService = new ServiceAuth();
        String result = authService.authenticateUser("example@example.com", "password123");
        assertEquals("Login successful", result);
    }
}
package AuthenticationTest;

import org.junit.jupiter.api.Test;

import Authentication.ServiceMail;
import Authentication.ServiceReset;
import Authentication.User;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.Mockito.*;

import java.util.HashMap;

public class TestServicePass {

    @Test
    public void testSendPasswordResetLink_ValidEmailInMap() {
        // Creating a mock ServiceMail
        ServiceMail mockServiceMail = mock(ServiceMail.class);
        when(mockServiceMail.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

        // Creating a mock user database to check for invalid user.
        HashMap<String, User> mockUsers = new HashMap<>();
        User user = new User("test@example.com", "oldPassword");
        mockUsers.put("test@example.com", user);

        // Creating a ServiceReset using mock ServiceMail and mock user database
        ServiceReset ServiceReset = new ServiceReset(mockServiceMail, mockUsers);

        // Test for sending password reset link for valid email in the database.
        boolean result = ServiceReset.sendPasswordResetLink("test@example.com");

        // Verifying that the sendMail method was called correctly
        verify(mockServiceMail).sendMail(eq("test@example.com"), eq("Password Reset Link"), anyString());
        assertTrue(result);
    }

    @Test
    public void testSendPasswordResetLink_InvalidEmail() {
        ServiceMail mockServiceMail = mock(ServiceMail.class);
        when(mockServiceMail.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

        
        HashMap<String, User> mockUsers = new HashMap<>();

        
        ServiceReset ServiceReset = new ServiceReset(mockServiceMail, mockUsers);

        // Test sending password reset link for invalid email
        boolean result = ServiceReset.sendPasswordResetLink("invalid@example.com");

        // Verify that sendMail method was not called for invalid email
        verify(mockServiceMail, never()).sendMail(anyString(), anyString(), anyString());
        assertFalse(result);
    }

    @Test
    public void testSendPasswordResetLink_ValidEmailNotInMap() {
        
        ServiceMail mockServiceMail = mock(ServiceMail.class);
        when(mockServiceMail.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

        
        HashMap<String, User> mockUsers = new HashMap<>();

        
        ServiceReset ServiceReset = new ServiceReset(mockServiceMail, mockUsers);

        // Test sending password reset link for valid email not in database.
        boolean result = ServiceReset.sendPasswordResetLink("test@example.com");

        // Verify that sendMail method was not called for email not in database.
        verify(mockServiceMail, never()).sendMail(anyString(), anyString(), anyString());
        assertFalse(result);
    }
}
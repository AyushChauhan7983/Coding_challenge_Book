package com.book.ControllerTest;

import com.book.Controller.UserController;
import com.book.Model.Users;
import com.book.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Create a mock user
        Users mockUser = new Users();
        mockUser.setUsername("testUser");
        mockUser.setPassword("password123");

        // Mock the service response
        when(userService.register(any(Users.class))).thenReturn(mockUser);

        // Call the controller method
        Users response = userController.register(mockUser);

        // Assertions
        assertNotNull(response);
        assertEquals("testUser", response.getUsername());
        assertEquals("password123", response.getPassword());
        verify(userService, times(1)).register(mockUser);
    }

    @Test
    void testLoginSuccess() {
        // Create a mock user
        Users mockUser = new Users();
        mockUser.setUsername("testUser");
        mockUser.setPassword("password123");

        // Mock the service response for successful login
        when(userService.verify(any(Users.class))).thenReturn("Login successful");

        // Call the controller method
        String response = userController.login(mockUser);

        // Assertions
        assertNotNull(response);
        assertEquals("Login successful", response);
        verify(userService, times(1)).verify(mockUser);
    }

    @Test
    void testLoginFailure() {
        // Create a mock user
        Users mockUser = new Users();
        mockUser.setUsername("testUser");
        mockUser.setPassword("wrongPassword");

        // Mock the service response for login failure
        when(userService.verify(any(Users.class))).thenReturn("Invalid credentials");

        // Call the controller method
        String response = userController.login(mockUser);

        // Assertions
        assertNotNull(response);
        assertEquals("Invalid credentials", response);
        verify(userService, times(1)).verify(mockUser);
    }
}

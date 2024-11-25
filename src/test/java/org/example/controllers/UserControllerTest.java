package org.example.controllers;

import org.example.models.User;
import org.example.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {

    @Test
    void testSignUp() {
        // Mock the repository
        UserRepository mockRepository = Mockito.mock(UserRepository.class);

        // Create a sample user
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Test User");
        mockUser.setUsername("testuser");
        mockUser.setPassword("password123");

        // Stub the save method
        Mockito.when(mockRepository.save(Mockito.any(User.class))).thenReturn(mockUser);

        // Create the controller
        userController controller = new userController(mockRepository);

        // Call the method
        String result = controller.signUp(mockUser);

        // Verify the interactions and assert the result
        Mockito.verify(mockRepository).save(mockUser);
        assertEquals("homePage", result, "The method should redirect to the homePage.");
    }
    @Test
    void testLoginSuccessful() {
        // Mock the repository
        UserRepository mockRepository = Mockito.mock(UserRepository.class);

        // Create a sample user
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("testuser");
        mockUser.setPassword("password123");

        // Stub the repository's findByUsername method
        Mockito.when(mockRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(mockUser));

        // Create the controller
        userController controller = new userController(mockRepository);

        // Mock the Model
        Model mockModel = Mockito.mock(Model.class);

        // Call the method
        String result = controller.login("testuser", "password123", mockModel);

        // Assert and verify
        assertEquals("redirect:/user/1/surveys", result, "The login method should redirect to the user's survey page.");
    }

    @Test
    void testLoginFailure() {
        // Mock the repository
        UserRepository mockRepository = Mockito.mock(UserRepository.class);

        // Stub the repository to return empty for a non-existent user
        Mockito.when(mockRepository.findByUsername("wronguser")).thenReturn(java.util.Optional.empty());

        // Create the controller
        userController controller = new userController(mockRepository);

        // Mock the Model
        Model mockModel = Mockito.mock(Model.class);

        // Call the method
        String result = controller.login("wronguser", "wrongpassword", mockModel);

        // Assert the result
        assertEquals("homePage", result, "The login method should redirect to the homePage on failure.");
        Mockito.verify(mockModel).addAttribute("error", "User not found");
    }

}

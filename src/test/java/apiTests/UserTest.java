package apiTests;

import controllers.BaseController;
import controllers.UserController;
import entities.response.UserResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    public static UserController userController;
    protected static BaseController baseController = new BaseController();

    @BeforeAll
    public static void setUp() {
        userController = baseController.getUserController();
    }

    @Test
    @DisplayName("Create a new user")
    public void createNewUserTest() {
        UserResponse userResponse = userController.createNewUser().as(UserResponse.class);

        assertAll("Check create a new user",
                () -> assertTrue(userResponse.getStatus().equals("active")),
                () -> assertEquals("active", userResponse.getStatus()),
                () -> assertEquals("male", userResponse.getGender())
        );
    }

    @Test
    @DisplayName("Get a new user")
    public void getNewUserTest() {
        UserResponse[] users = userController.getNewUser().as(UserResponse[].class);
        List<UserResponse> userResponse = Arrays.asList(users);
        userResponse.forEach(user -> System.out.println("Id " + user.getId()));

        assertFalse(userResponse.isEmpty());
    }
}


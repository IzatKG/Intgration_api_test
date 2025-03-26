package apiTests;

import controllers.BaseController;
import controllers.UserController;
import entities.response.UserResponse;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {
    public static UserController userController;
    protected static BaseController baseController = new BaseController();
    public static int userId;
    @BeforeAll
    public static void setUp() {
        userController = baseController.getUserController();
    }

    @Test
    @Order(0)
    @DisplayName("Create a new user")
    public void createNewUserTest() {
        UserResponse userResponse = userController.createNewUser().as(UserResponse.class);
        userId = userResponse.getId();

        assertAll("Check create a new user",
                () -> assertTrue(userResponse.getStatus().equals("active")),
                () -> assertEquals("active", userResponse.getStatus()),
                () -> assertEquals("male", userResponse.getGender())
        );
    }

    @Test
    @Order(1)
    @DisplayName("Get a new user by id")
    public void getNewUserByIDTest() {
        UserResponse users = userController.getNewUserById(userId).as(UserResponse.class);

    }

    @Test
    @Order(2)
    @DisplayName("Get a new user")
    public void getNewUserTest() {
        UserResponse[] users = userController.getNewUser().as(UserResponse[].class);
        List<UserResponse> userResponse = Arrays.asList(users);
        userResponse.forEach(user -> System.out.println("Id " + user.getId()));

        assertFalse(userResponse.isEmpty());
    }


}


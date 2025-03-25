package apiTests;

import controllers.BaseController;
import controllers.UserController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTest {
public static UserController userController;
    protected static BaseController baseController = new BaseController();

    @BeforeAll
    public static void setUp(){
        userController = baseController.getUserController();
    }

    @Test
    public void createNewUserTest(){
        userController.createNewUser();
    }
}

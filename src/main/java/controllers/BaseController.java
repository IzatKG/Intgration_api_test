package controllers;

import config.ConfigProperties;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

@Getter
public class BaseController {
    public static ConfigProperties config = ConfigFactory.create(ConfigProperties.class);

    private final UserController userController;

    public BaseController(){

        this.userController = new UserController(config.baseUrl());
    }
}

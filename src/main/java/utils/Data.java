package utils;

import com.github.javafaker.Faker;
import entities.request.GoRequestBody;

public class Data {
static Faker faker= new Faker();
    public static GoRequestBody createRequestBody(){
        return GoRequestBody.builder()
                .name(faker.name().firstName())
                .email(faker.internet().emailAddress())
                .gender("male")
                .status("active")
                .build();
    }
}

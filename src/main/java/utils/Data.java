package utils;

import entities.request.GoRequestBody;

public class Data {

    public static GoRequestBody createRequestBody(){
        return GoRequestBody.builder()
                .name("Maksyt1675")
                .email("ouyp45oj@gmail.com")
                .gender("male")
                .status("active")
                .build();
    }
}

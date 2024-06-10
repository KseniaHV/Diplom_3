package org.example;

import io.restassured.http.ContentType;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

public class MethodApi {
    public static final String base_URL = "https://stellarburgers.nomoreparties.site";
    public static final String DELETE_PATH = "/api/auth/user";
    public static final String CREATE_PATH = "/api/auth/register";
    public void userCreate(CreateUser user) {
        given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(base_URL)
                .and()
                .body(user)
                .when()
                .post(CREATE_PATH)
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }
    public void userDelete(String accessToken) {
        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .baseUri(base_URL)
                .when()
                .delete(DELETE_PATH)
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED);
    }
}

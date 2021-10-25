package io.github.dougllasfps.quarkussocial.rest;

import io.github.dougllasfps.quarkussocial.domain.model.User;
import io.github.dougllasfps.quarkussocial.rest.dto.CreateUserRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class UserResourceTest {

    @Test
    public void createUser(){
        var user = new User();
        user.setName("Dougllas");
        user.setAge(33);

        var response = given()
            .contentType(ContentType.JSON)
            .body(user)
            .when().post("/users")
            .then().extract().response();

        assertEquals(201, response.statusCode());
        assertNotNull(response.jsonPath().getString("id"));

    }

    @Test
    public void createUserWithError(){
        var user = new CreateUserRequest();
        user.setName(null);
        user.setAge(null);

        var response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/users")
                .then().extract().response();

        List<Map<String,String>> errors = response.jsonPath().getList("errors");

        assertEquals(422, response.statusCode());
        assertEquals("Validation Error" , response.jsonPath().getString("message"));
        assertEquals("Name is Required" , errors.get(0).get("message"));
        assertEquals("Age is Required" , errors.get(1).get("message"));

    }
}
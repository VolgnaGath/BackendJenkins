package org.example.Lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.dto.response.ClassifyCuisineResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class PostTests extends TestClass{
    @Test
    void postTests() {
        ClassifyCuisineResponse classifyCuisineResponse = given()
                .spec(requestSpecification)
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "burger")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .extract().as(ClassifyCuisineResponse.class);
        assertThat(classifyCuisineResponse.getCuisine(), equalTo("American"));


        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "burger")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(401)
                .header("Connection", "keep-alive")
                .body("message", equalTo("You are not authorized. Please read https://spoonacular.com/food-api/docs#Authentication"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));

        ClassifyCuisineResponse classifyCuisineResponse1 = given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Mushroom Jerky")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .extract()
                .as(ClassifyCuisineResponse.class);
        assertThat(classifyCuisineResponse1.getCuisine(), equalTo("Mediterranean"));

        ClassifyCuisineResponse classifyCuisineResponse2 = given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "german")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .extract()
                .as(ClassifyCuisineResponse.class);
        assertThat(classifyCuisineResponse2.getCuisine(), equalTo("European"));


        ClassifyCuisineResponse classifyCuisineResponse3 = given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Potato Pie")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .extract()
                .as(ClassifyCuisineResponse.class);
        assertThat(classifyCuisineResponse3.getCuisine(), equalTo("Mediterranean"));


    }
    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "e0c0ada9790698101d2e4430ef1adc9027a774f0")
                .spec(requestSpecification)
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post( getBaseUrl()+ "mealplanner/1f7c31f6-95e2-4811-af91-275cccf0fca6/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "e0c0ada9790698101d2e4430ef1adc9027a774f0")
                .spec(requestSpecification)
                .delete(getBaseUrl() + "mealplanner/1f7c31f6-95e2-4811-af91-275cccf0fca6/items/" + id)
                .then()
                .spec(responseSpecification);
    }
}

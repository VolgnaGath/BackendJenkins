package org.example.Lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.dto.response.Result;
import org.dto.response.SearchResponse;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class SearchTests extends TestClass {
    @Test
    void getTests() {
        given()
                .queryParam("query", "burger")
                .queryParam("maxFat", "60")
                .spec(requestSpecification)
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);


        given()
                .queryParam("author", "swasthi")
                .queryParam("apiKey", getApiKey())
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given()
                .queryParam("query", "burger")
                .queryParam("diet", "vegetarian")
                .queryParam("maxReadyTime", "10")
                .queryParam("minCalories", "74")
                .spec(requestSpecification)
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given()
                .queryParam("query", "pizza")
                .queryParam("includeIngredients", "tomato,cheese")
                .queryParam("fillIngredients", "true")
                .spec(requestSpecification)
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);

       List<SearchResponse> searchResponse = Arrays.asList(given()
                .spec(requestSpecification)
                .queryParam("cuisine", "german")
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .extract()
                .as(SearchResponse.class));
        // searchResponse.forEach(searchResponse1 -> searchResponse1.getResults().forEach(result -> assertThat(result.getTitle(), containsString("German Rhubarb Cake with Meringue"))));
        assertThat(searchResponse.stream().findFirst().get().getResults().stream().findFirst().get().getTitle(), containsString("Pork Schnitzel And Apple Salad"));


    }

}

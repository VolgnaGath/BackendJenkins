package org.example.Lesson3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public abstract class TestClass {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    protected static ResponseSpecification responseSpecification;
    protected static RequestSpecification requestSpecification;

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties.properties");
        prop.load(configFile);
        apiKey =  prop.getProperty("apiKey");
        baseUrl= prop.getProperty("base_url");

    }
    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
    @BeforeEach
    void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Connection", "keep-alive")
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", getApiKey())
                .log(LogDetail.ALL)
                .build();
    }



    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}


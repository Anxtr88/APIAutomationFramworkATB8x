package com.APITestingPrograms.base;

import com.APITestingPrograms.asserts.AssertActions;
import com.APITestingPrograms.endpoints.APIConstants;
import com.APITestingPrograms.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public Response response;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jasonpath;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp(){
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();
    }

    public String getToken(){
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        String payload = payloadManager.setAuthPayload();

        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload).when().post();

        String token = payloadManager.getTokenFromJson(response.asString());
        return token;

    }


}

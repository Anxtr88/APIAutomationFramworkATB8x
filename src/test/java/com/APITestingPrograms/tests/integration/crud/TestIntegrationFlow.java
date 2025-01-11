package com.APITestingPrograms.tests.integration.crud;

import com.APITestingPrograms.base.BaseTest;
import com.APITestingPrograms.endpoints.APIConstants;
import com.APITestingPrograms.pojos.Booking;
import com.APITestingPrograms.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TestIntegrationFlow extends BaseTest {

    @Test(groups = "integration", priority = 1)
    @Owner("Promode")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) {
        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.payloadBookingAsString()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/"+ bookingid);
        response = RestAssured.given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isEqualTo("James").isNotBlank();

    }


    @Test(groups = "integration", priority = 3)
    @Owner("Promode")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {

        String token = getToken();
        iTestContext.setAttribute("token", token);
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Pramod");
        assertThat(booking.getLastname()).isEqualTo("Dutta");

    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {

        String token = (String)iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");


        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);



    }
}

package com.APITestingPrograms.tests.integration.crud;

import com.APITestingPrograms.base.BaseTest;
import com.APITestingPrograms.endpoints.APIConstants;
import com.APITestingPrograms.pojos.Booking;
import com.APITestingPrograms.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;


public class testVerifyCreateBookingPOST01 extends BaseTest {


    @Owner("Anster")
    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA-1")
    @Description("Verify the post request")
    @Test
    public void testVerifyCreateBookingPOST01() {

        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);


        response =  RestAssured.given(requestSpecification)
                .when().body(payloadManager.payloadBookingAsString()).post();

        validatableResponse =  response.then().log().all();
        validatableResponse.statusCode(200);

        //Default Rest Assured - Validation -
        validatableResponse.body("booking.firstname", Matchers.equalTo("James"));

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "James");

        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("James");






    }
}

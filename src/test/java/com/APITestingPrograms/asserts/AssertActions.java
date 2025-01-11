package com.APITestingPrograms.asserts;

// Common Assertions - Which can be reused.

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;


public class AssertActions {

    public void verifyResponse(String actual, String expected, String description){
        assertEquals(actual, expected, description);

    }

    public void verifyResponse(int actual, int expected, String description){
        assertEquals(actual, expected, description);

    }

    public void verifyStatusCode(Response response, Integer expected){
        assertEquals(response.getStatusCode(), expected);

    }

    public void verifyStringKey(String KeyExpect, String KeyActual){

        assertThat(KeyExpect).isNotNull();
        assertThat(KeyExpect).isNotNull().isNotBlank();
        assertThat(KeyExpect).isEqualTo(KeyActual);

    }
}

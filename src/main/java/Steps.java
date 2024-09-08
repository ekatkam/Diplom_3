import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.junit.Assert.*;

public class Steps {

    @Step("Get response status code")
    public int getStatusCode(ValidatableResponse response) {
        int responseStatusCode = response.extract().statusCode();
        return responseStatusCode;
    }

    @Step("Compare response status code with successful status code")
    public void compareResponseStatusCodeWith200(int responseStatusCode) {
        assertEquals(200,responseStatusCode);
    }

    @Step("Check that success == true")
    public void checkSuccessIsTrue(ValidatableResponse response) {
        boolean success = response.extract().path("success");
        assertTrue(success);
    }
    @Step("Get access token")
    public String getAccessToken(ValidatableResponse response) {
        String accessToken = response.extract().path("accessToken");
        return accessToken;
    }
}

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private MainPage objMainPage;
    private LoginPage objLoginPage;
    private HeaderElement objHeaderElement;
    private RegisterPage objRegisterPage;
    private ForgotPasswordPage objForgotPasswordPage;
    private UserClient userClient;
    private User user;
    private Steps step;
    private String accessToken;

    @Before
    public void setUp() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objHeaderElement = new HeaderElement(driver);
        objRegisterPage = new RegisterPage(driver);
        objForgotPasswordPage = new ForgotPasswordPage(driver);
        userClient = new UserClient();
        user = UserGenerator.getUserData();
        step = new Steps();
        ValidatableResponse userCreateResponse = userClient.create(user);
        int userCreateResponseStatusCode = step.getStatusCode(userCreateResponse);
        step.compareResponseStatusCodeWith200(userCreateResponseStatusCode);
        accessToken = step.getAccessToken(userCreateResponse);
    }

    @After
    public void cleanUp() {
        ValidatableResponse userDeleteResponse = userClient.delete(accessToken);
        step.checkSuccessIsTrue(userDeleteResponse);
    }


    @Test
    @DisplayName("Authorization with login button on the main page")
    @Description("Test checks authorization by clicking login button on the main page")
    public void authorizationWithLoginButtonOnMainPage() {
        objMainPage.waitForMainPageLoad();
        objMainPage.clickLoginButton();
        objLoginPage.waitForLoginPageLoad();
        objLoginPage.fillInAuthorizationForm(user.getEmail(), user.getPassword());
        objLoginPage.clickLoginButton();
        objMainPage.waitForMainPageLoad();
        boolean result = objMainPage.checkToMakeAnOrderButtonIsDisplayed();
        assertTrue(result);
    }

    @Test
    @DisplayName("Authorization with login button on the personal account page")
    @Description("Test checks authorization by clicking login button on the personal account page")
    public void authorizationWithPersonalAccountButton() {
        objHeaderElement.clickPersonalAccount();
        objLoginPage.waitForLoginPageLoad();
        objLoginPage.fillInAuthorizationForm(user.getEmail(), user.getPassword());
        objLoginPage.clickLoginButton();
        objMainPage.waitForMainPageLoad();
        boolean result = objMainPage.checkToMakeAnOrderButtonIsDisplayed();
        assertTrue(result);
    }

    @Test
    @DisplayName("Authorization with login button on the registration page")
    @Description("Test checks authorization by clicking login button on the registration page")
    public void authorizationWithLoginButtonInRegistrationForm() {
        objHeaderElement.clickPersonalAccount();
        objLoginPage.waitForLoginPageLoad();
        objLoginPage.clickToRegisterLink();
        objRegisterPage.clickLoginLink();
        objLoginPage.fillInAuthorizationForm(user.getEmail(), user.getPassword());
        objLoginPage.clickLoginButton();
        objMainPage.waitForMainPageLoad();
        boolean result = objMainPage.checkToMakeAnOrderButtonIsDisplayed();
        assertTrue(result);
    }

    @Test
    @DisplayName("Authorization with login button on the password recovery page")
    @Description("Test checks authorization by clicking login button on the password recovery page")
    public void authorizationWithLoginButtonInPasswordRecoveryForm() {
        objMainPage.waitForMainPageLoad();
        objMainPage.clickLoginButton();
        objLoginPage.waitForLoginPageLoad();
        objLoginPage.clickForgotPasswordLink();
        objForgotPasswordPage.clickLoginLink();
        objLoginPage.fillInAuthorizationForm(user.getEmail(), user.getPassword());
        objLoginPage.clickLoginButton();
        objMainPage.waitForMainPageLoad();
        boolean result = objMainPage.checkToMakeAnOrderButtonIsDisplayed();
        assertTrue(result);
    }
}

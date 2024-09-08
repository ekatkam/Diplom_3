import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pages.HeaderElement;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private HeaderElement objHeaderElement;
    private LoginPage objLoginPage;
    private RegisterPage objRegisterPage;
    private MainPage objMainPage;
    private UserClient userClient;
    private Steps step;
    private User user;


    @Before
    public void setUp() {
        objHeaderElement = new HeaderElement(driver);
        objLoginPage = new LoginPage(driver);
        objRegisterPage = new RegisterPage(driver);
        objMainPage = new MainPage(driver);
        user = UserGenerator.getUserData();
    }


    @Test
    public void successfulRegistration() {
        step = new Steps();
        userClient = new UserClient();
        objHeaderElement.waitForButtonLoad();
        objHeaderElement.clickPersonalAccount();
        objLoginPage.waitForLoginPageLoad();
        objLoginPage.clickToRegisterLink();
        objRegisterPage.fillInRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        objRegisterPage.clickToRegisterButton();
        boolean result = objLoginPage.checkLoginHeaderIsDisplayed();
        assertTrue(result);

        objLoginPage.fillInAuthorizationForm(user.getEmail(), user.getPassword());
        objLoginPage.clickLoginButton();
        objMainPage.waitForMainPageLoad();
        String accessToken = ((JavascriptExecutor)driver).executeScript((
                "return window.localStorage.getItem('accessToken')")).toString();

        ValidatableResponse userDeleteResponse = userClient.delete(accessToken);
        step.checkSuccessIsTrue(userDeleteResponse);
     }

    @Test
    public void unsuccessfulRegistrationWithWrongPassword() {
        user.setPassword("12345");
        objHeaderElement.waitForButtonLoad();
        objHeaderElement.clickPersonalAccount();
        objLoginPage.waitForLoginPageLoad();
        objLoginPage.clickToRegisterLink();
        objRegisterPage.fillInRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        objRegisterPage.clickToRegisterButton();
        boolean result = objRegisterPage.checkPasswordErrorIsDisplayed();
        assertTrue(result);
    }
}

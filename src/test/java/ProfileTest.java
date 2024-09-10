import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;

import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {

    private MainPage objMainPage;
    private LoginPage objLoginPage;
    private HeaderElement objHeaderElement;
    private ProfilePage objProfilePage;
    private UserClient userClient;
    private User user;
    private Steps step;
    private String accessToken;

    @Before
    public void setUp() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objHeaderElement = new HeaderElement(driver);
        objProfilePage = new ProfilePage(driver);
        userClient = new UserClient();
        user = UserGenerator.getUserData();
        step = new Steps();
        ValidatableResponse userCreateResponse = userClient.create(user);
        int userCreateResponseStatusCode = step.getStatusCode(userCreateResponse);
        step.compareResponseStatusCodeWith200(userCreateResponseStatusCode);
        accessToken = step.getAccessToken(userCreateResponse);
        objMainPage.clickLoginButton();
        objLoginPage.fillInAuthorizationForm(user.getEmail(), user.getPassword());
        objLoginPage.clickLoginButton();
    }

    @After
    public void cleanUp() {
        ValidatableResponse userDeleteResponse = userClient.delete(accessToken);
        step.checkSuccessIsTrue(userDeleteResponse);
    }

    @Test
    @DisplayName("Transition to personal account")
    @Description("Test checks transition to personal account page by clicking personal account button")
    public void transitionToPersonalAccount() {
        objHeaderElement.clickPersonalAccount();
        boolean result = objProfilePage.checkProfileMenuIsDisplayed();
        assertTrue(result);
    }

    @Test
    @DisplayName("Transition from personal account to constructor")
    @Description("Test checks transition from personal account page to constructor on the main page by clicking constructor button")
    public void transitionFromPersonalAccountToConstructorByConstructor() {
        objHeaderElement.clickPersonalAccount();
        objHeaderElement.clickConstructor();
        boolean result = objMainPage.checkMakeBurgerHeaderIsDisplayed();
        assertTrue(result);
    }

    @Test
    @DisplayName("Transition from personal account to constructor")
    @Description("Test checks transition from personal account page to constructor on the main page by clicking Stellar Burger logo")
    public void transitionFromPersonalAccountToConstructorByLogo() {
        objHeaderElement.clickPersonalAccount();
        objHeaderElement.clickLogo();
        boolean result = objMainPage.checkMakeBurgerHeaderIsDisplayed();
        assertTrue(result);
    }

    @Test
    @DisplayName("Logout")
    @Description("Test checks transition from personal account page to login page by clicking logout button")
    public void logoutFromPersonalAccount() {
        objHeaderElement.clickPersonalAccount();
        objProfilePage.clickLogoutButton();
        boolean result = objLoginPage.checkLoginHeaderIsDisplayed();
        assertTrue(result);
    }
}

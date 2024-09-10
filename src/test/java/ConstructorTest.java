import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

public class ConstructorTest extends BaseTest {
    MainPage objMainPage;

    @Before
    public void setUp() {
        objMainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Transition to Buns in constructor")
    @Description("Test checks transition to the list of buns in constructor")
    public void checkTransitionToBuns() {
        objMainPage.clickBuns();
        objMainPage.checkBunsDisplayed();
    }

    @Test
    @DisplayName("Transition to Sauces in constructor")
    @Description("Test checks transition to the list of sauces in constructor")
    public void checkTransitionToSauces() {
        objMainPage.clickSauces();
        objMainPage.checkSaucesDisplayed();
    }

    @Test
    @DisplayName("Transition to Fillings in constructor")
    @Description("Test checks transition to the list of fillings in constructor")
    public void checkTransitionToFillings() {
        objMainPage.clickFillings();
        objMainPage.checkFillingsDisplayed();
    }
}

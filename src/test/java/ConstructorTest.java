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
    public void checkTransitionToBuns() {
        objMainPage.clickBuns();
        objMainPage.checkBunsDisplayed();
    }

    @Test
    public void checkTransitionToSauces() {
        objMainPage.clickSauces();
        objMainPage.checkSaucesDisplayed();
    }

    @Test
    public void checkTransitionToFillings() {
        objMainPage.clickFillings();
        objMainPage.checkFillingsDisplayed();
    }

}

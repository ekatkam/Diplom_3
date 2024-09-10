package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

public class MainPage extends BasePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By makeAnOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private By makeBurgerHeader = By.xpath(".//h1[text()='Соберите бургер']");
    private By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        new Actions(driver)
                .moveToElement(button)
                .perform();
        wait
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    @Step("Check that To make an order button is displayed")
    public boolean checkToMakeAnOrderButtonIsDisplayed() {
        boolean result = driver.findElement(makeAnOrderButton).isDisplayed();
        return result;
    }

    public void waitForMainPageLoad() {
        wait
                .until(ExpectedConditions.visibilityOfElementLocated(makeBurgerHeader));
    }

    @Step("Check that Make burger header is displayed")
    public boolean checkMakeBurgerHeaderIsDisplayed() {
        boolean result = driver.findElement(makeBurgerHeader).isDisplayed();
        return result;
    }

    @Step("Click on buns tab")
    public void clickBuns() {
        WebElement button = driver.findElement(bunsTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    @Step("Click on sauces tab")
    public void clickSauces() {
        WebElement button = driver.findElement(saucesTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    @Step("Click on fillings tab")
    public void clickFillings() {
        WebElement button = driver.findElement(fillingsTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    @Step("Check buns are displayed")
    public void checkBunsDisplayed() {
        String className = driver.findElement(bunsTab).getAttribute("class");
        assertTrue(className.contains("tab_tab_type_current__2BEPc"));
    }

    @Step("Check sauces are displayed")
    public void checkSaucesDisplayed() {
        String className = driver.findElement(saucesTab).getAttribute("class");
        assertTrue(className.contains("tab_tab_type_current__2BEPc"));
    }

    @Step("Check fillings are displayed")
    public void checkFillingsDisplayed() {
        String className = driver.findElement(fillingsTab).getAttribute("class");
        assertTrue(className.contains("tab_tab_type_current__2BEPc"));
    }
}

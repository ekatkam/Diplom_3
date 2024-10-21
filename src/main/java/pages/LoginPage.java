package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private By toRegisterLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private By loginHeader = By.xpath(".//h2[text()='Вход']");
    private By emailField = By.name("name");
    private By passwordField = By.name("Пароль");
    private By loginButton = By.xpath(".//button[text()='Войти']");
    private By forgotPasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoginPageLoad(){
        wait
                .until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
    }

    @Step("Click link to register link")
    public void clickToRegisterLink() {
        WebElement link = driver.findElement(toRegisterLink);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", link);
        new Actions(driver)
                .moveToElement(link)
                .perform();
        wait
                .until(ExpectedConditions.elementToBeClickable(toRegisterLink));
        link.click();
    }

    @Step("Check Login header is displayed")
    public boolean checkLoginHeaderIsDisplayed() {
        waitForLoginPageLoad();
        boolean result = driver.findElement(loginHeader).isDisplayed();
        return result;
    }

    @Step("Enter email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Login")
    public void fillInAuthorizationForm(String email, String password) {
        enterEmail(email);
        enterPassword(password);
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

    @Step("Click forgot password link")
    public void clickForgotPasswordLink() {
        WebElement link = driver.findElement(forgotPasswordLink);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", link);
        new Actions(driver)
                .moveToElement(link)
                .perform();
        wait
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        link.click();
    }
}

package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    private By nameField = By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][1]//input[@name='name']");
    private By emailFied = By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][2]//input[@name='name']");
    private By passwordField = By.name("Пароль");
    private By passwordError = By.xpath(".//p[text()='Некорректный пароль']");
    private By toRegisterButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By loginLink = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter name")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Enter email")
    public void enterEmail(String email) {
        driver.findElement(emailFied).sendKeys(email);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click button to register")
    public void clickToRegisterButton() {
        WebElement button = driver.findElement(toRegisterButton);
        new Actions(driver)
                .moveToElement(button)
                .perform();
        wait
                .until(ExpectedConditions.elementToBeClickable(toRegisterButton));
        button.click();
    }

    @Step("Check password error is displayed")
    public boolean checkPasswordErrorIsDisplayed() {
        boolean result = driver.findElement(passwordError).isDisplayed();
        return result;
    }

    @Step("Registration")
    public void fillInRegistrationForm(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Click login link")
    public void clickLoginLink() {
        WebElement link = driver.findElement(loginLink);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", link);
        driver.findElement(loginLink).click();
    }
}

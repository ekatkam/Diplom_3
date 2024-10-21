package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ForgotPasswordPage extends BasePage {

    private By loginLink = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click login link")
    public void clickLoginLink() {
        WebElement link = driver.findElement(loginLink);
        new Actions(driver)
                .moveToElement(link)
                .perform();
        link.click();
    }
}

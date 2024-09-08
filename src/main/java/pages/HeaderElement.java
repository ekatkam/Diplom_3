package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderElement extends BasePage {

    private By personalAccount = By.xpath(".//a[@href = '/account']");
    private By constructor = By.xpath(".//p[text()='Конструктор']/parent::a");
    private By logo = By.cssSelector("body > div > div > header > nav > div > a > svg");

    public HeaderElement(WebDriver driver) {
        super(driver);
    }

    public void waitForButtonLoad(){
        wait
                .until(ExpectedConditions.elementToBeClickable(personalAccount));
    }
    @Step("Click Personal account button")
    public void clickPersonalAccount() {
        driver.manage().window().maximize();
        wait
                .until(ExpectedConditions.elementToBeClickable(personalAccount));
        WebElement button = driver.findElement(personalAccount);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        new Actions(driver)
                .moveToElement(button)
                .perform();
        button.click();
    }

    @Step("Click Constructor button")
    public void clickConstructor() {
        WebElement button = driver.findElement(constructor);
        new Actions(driver)
                .moveToElement(button)
                .perform();
        button.click();
    }

    @Step("Click logo")
    public void clickLogo() {
        WebElement button = driver.findElement(logo);
        new Actions(driver)
                .moveToElement(button)
                .perform();
        button.click();
    }
}

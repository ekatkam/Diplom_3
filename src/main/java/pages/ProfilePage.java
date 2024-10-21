package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    private By profileMenu = By.className("Account_list__3KQQf");
    private By logoutButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoadProfilePage() {
        wait
                .until(ExpectedConditions.visibilityOfElementLocated(profileMenu));
    }

    @Step("Check that profile menu is displayed")
    public boolean checkProfileMenuIsDisplayed() {
        waitForLoadProfilePage();
        boolean result = driver.findElement(profileMenu).isDisplayed();
        return result;
    }

    @Step("Click logout Button button")
    public void clickLogoutButton() {
        waitForLoadProfilePage();
        WebElement button = driver.findElement(logoutButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait
                .until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }
}

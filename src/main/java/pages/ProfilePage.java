package pages;

import org.example.EnvConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final By pageProfile = By.xpath(".//a[text()='Профиль']");
    private final By buttonExit = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public void checkProfile() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(pageProfile));
        boolean isInProfile = driver.findElement(pageProfile).isDisplayed();
        Assert.assertTrue("Пользователь не перешел на страницу профиля.", isInProfile);
    }
    public LoginPage clickButtonExit() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonExit));

        driver.findElement(buttonExit).click();
        return new LoginPage(driver);
    }

}

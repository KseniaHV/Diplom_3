package pages;

import org.example.EnvConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By fieldName = By.cssSelector(("input[name='name']"));
    private final By fieldPassword = By.cssSelector(("input[name='Пароль']"));
    private final By buttonLogin = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginUser(String mail, String password) {
        driver.findElement(fieldName).sendKeys(mail);  //Логинимся
        driver.findElement(fieldPassword).sendKeys(password);

    }
    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonLogin));
    }
    public void check() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonLogin));
        boolean isLoginButtonDisplayed = driver.findElement(buttonLogin).isDisplayed();
        Assert.assertTrue("Кнопка «Войти» не отображается после регистрации.", isLoginButtonDisplayed);
    }

}

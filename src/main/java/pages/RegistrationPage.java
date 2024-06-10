package pages;

import org.example.EnvConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final By nameField = By.cssSelector(("input[name='name']"));
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input[@type='text']");
    private final By passwordField = By.cssSelector(("input[name='Пароль']"));
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By fieldError = By.xpath(".//p[@class='input__error text_type_main-default']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public void registrationForm(String name, String mail, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(mail);
        driver.findElement(passwordField).sendKeys(password);
    }
    public LoginPage buttonRegistration() {
        driver.findElement(registrationButton).click();
        return new LoginPage(driver);
    }
    public void invalidRegistration(String name, String mail, String pass) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(mail);
        driver.findElement(passwordField).sendKeys(pass);
    }
    public void error() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldError));

        boolean isErrorDisplayed = driver.findElement(fieldError).isDisplayed();
        Assert.assertTrue("Сообщение об ошибке не отображается при неправильном пароле.", isErrorDisplayed);
    }
}

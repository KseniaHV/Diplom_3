package regist;

import io.qameta.allure.junit4.DisplayName;
import org.example.DriverRule;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;
public class RegistrationNegativeTests {
    private final String NAME = "Дима48";
    private final String EMAIL = "Dmitrii48@yandex.ru";
    private final String INCORRECT_PASSWORD = "123";
    @Rule
    public DriverRule driverRule = new DriverRule();
    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void RegistrationIncorrectPass(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        
        main.openPage();

        main.clickButtonPersonalArea();
        RegistrationPage registration = main.clickRegistration();
        registration.invalidRegistration(NAME, EMAIL, INCORRECT_PASSWORD);
        registration.buttonRegistration();
        registration.error();
    }
}

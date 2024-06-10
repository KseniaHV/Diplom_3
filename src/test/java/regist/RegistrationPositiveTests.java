package regist;

import io.qameta.allure.Description;
import org.example.DriverRule;
import org.example.MethodApi;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;
public class RegistrationPositiveTests {
    public static final String NAME = "Дмитрий600";
    public static final String EMAIL = "Dmitrii600@yandex.ru";
    public static final String PASSWORD = "pass1234";
    @Rule
    public DriverRule driverRule = new DriverRule();
    private static String accessToken;
    private static final MethodApi check = new MethodApi();
    @Test
    @Description("Проверка успешной регистрации")
    public void userRegistration(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);

        main.openPage();

        main.clickButtonPersonalArea();
        RegistrationPage registration = main.clickRegistration();
        registration.registrationForm(NAME, EMAIL, PASSWORD);
        LoginPage login = registration.buttonRegistration();
        login.check();
    }
    @Test
    @Description("Логинимся чтобы получить токен")
    public void getItToken(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);

        main.openPage();

        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
    }
    @After
    public void deleteUser() {
        if (accessToken != null) {
            check.userDelete(accessToken);
        }
    }
}

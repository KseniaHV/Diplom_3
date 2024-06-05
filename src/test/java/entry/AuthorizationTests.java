package entry;

import io.qameta.allure.junit4.DisplayName;
import org.example.CreateUser;
import org.example.DriverRule;
import org.example.MethodApi;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;
public class AuthorizationTests {
    private final String EMAIL = "Dmitrii999@yandex.ru";
    private final String PASSWORD = "pass1234";
    @Rule
    public DriverRule driverRule = new DriverRule();
    private static String accessToken;
    private static final MethodApi check = new MethodApi();
    @BeforeClass
    public static void createUser() {
        CreateUser user = CreateUser.createdUser();
        check.userCreate(user);
    }
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginButton(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);

        main.openPage();

        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        main.checkLog();

    }
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void personalAccountButton(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);

        main.openPage();

        LoginPage login = main.clickButtonArea(); 
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        main.checkLog();
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void buttonRegisterForm(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        
        main.openPage();

        main.clickButtonLoginAcc();
        main.clickButtonRegistration();
        LoginPage login = main.clickButtonToComeIn();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        main.checkLog();
    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void passwordRecoveryButton(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);

        main.openPage();

        main.clickButtonLoginAcc();
        main.clickRestorePassword();
        LoginPage login = main.clickButtonToComeIn();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        main.checkLog();

    }
    @AfterClass
    public static void deleteUser() {
        if (accessToken != null) {
            check.userDelete(accessToken);
        }
    }
}

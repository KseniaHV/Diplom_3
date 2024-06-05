package swype;

import io.qameta.allure.Description;
import org.example.CreateUser;
import org.example.DriverRule;
import org.example.MethodApi;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;
public class PageTransitionTests {

    public static final String EMAIL = "Dmitrii999@yandex.ru";
    public static final String PASSWORD = "pass1234";
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
    @Description("Проверка перехода по клику на «Личный кабинет»")
    public void clickPersonalAccount(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);

        main.openPage();
        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        ProfilePage profile = main.clickButtonPersonalAcc();
        profile.checkProfile();
    }
    @Test
    @Description("Проверка перехода из личного кабинета в конструктор")
    public void goToConstructor(){
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);

        main.openPage();
        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        ProfilePage profile = main.clickButtonPersonalAcc();
        profile.checkProfile();
        main.goConstructor();
        main.waitLoadingPage();
        main.constructorCheck();

    }
    @Test
    @Description("Проверка перехода из личного кабинета в конструктор через логотип Stellar Burgers")
    public void clickLogoStellarBurgers() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);

        main.openPage();
        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        ProfilePage profile = main.clickButtonPersonalAcc();
        profile.checkProfile();
        main.navigationLogo();
        main.waitLoadingPage();
        main.navigationLogoCheck();
    }
    @AfterClass
    public static void deleteUser() {
        if (accessToken != null) {
            check.userDelete(accessToken);
        }
    }
}


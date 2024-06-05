package exit;

import io.qameta.allure.Description;
import org.example.CreateUser;
import org.example.DriverRule;
import org.example.MethodApi;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;
public class LogOutTests {
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
    @Description("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void clickButtonExit() {
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
        profile.clickButtonExit();
        login.check();
    }
    @AfterClass
    public static void deleteUser() {
        if (accessToken != null) {
            check.userDelete(accessToken);
        }
    }
}

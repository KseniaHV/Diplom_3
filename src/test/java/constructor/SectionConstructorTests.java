package constructor;

import io.qameta.allure.Description;
import org.example.CreateUser;
import org.example.DriverRule;
import org.example.MethodApi;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;
public class SectionConstructorTests {
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
    @Description("Проверка перехода к разделу «Булки»")
    public void goBunsSection() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        var ingredientBuns = main.tabBuns();
        var ingredientSauces = main.tabSauces();

        main.openPage();

        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        main.clickSauces(ingredientSauces);  //Кликаем для перехода на раздел Соусы, чтобы потом кликнуть на Булки, так как изначально открыт раздел с Булками
        main.clickBuns(ingredientBuns);      //Кликаем на раздел с Булками
        main.checkBuns(ingredientBuns);
    }
    @Test
    @Description("Проверка перехода к разделу «Соусы»")
    public void goSaucesSection() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        var ingredientSauces = main.tabSauces();

        main.openPage();

        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        main.clickSauces(ingredientSauces);
        main.checkSauces(ingredientSauces);
    }
    @Test
    @Description("Проверка перехода к разделу «Начинки»")
    public void goFillingsSection() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        var ingredientFillings = main.tabFillings();

        main.openPage();

        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
        main.clickFillings(ingredientFillings);
        main.checkFillings(ingredientFillings);
    }
    @AfterClass
    public static void deleteUser() {
        if (accessToken != null) {
            check.userDelete(accessToken);
        }
    }
}

package constructor;

import io.qameta.allure.Description;
import org.example.CreateUser;
import org.example.DriverRule;
import org.example.MethodApi;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.time.Duration;

public class SectionConstructorTests {
    private final String EMAIL = "Dmitrii600@yandex.ru";
    private final String PASSWORD = "pass1234";
    @Rule
    public DriverRule driverRule = new DriverRule();
    private String accessToken;
    private final MethodApi check = new MethodApi();
    @Before
    public void createUser() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        MainPage main = new MainPage(driver);

        CreateUser user = CreateUser.createdUser();
        check.userCreate(user);

        main.openPage();

        LoginPage login = main.clickButtonLoginAcc();
        login.loginUser(EMAIL, PASSWORD);
        login.clickLoginButton();
        main.waitLoadingPage();
        main.getToken();
        accessToken = main.getAccessToken();
    }
    @Test
    @Description("Проверка перехода к разделу «Булки»")
    public void goBunsSection() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        var ingredientBuns = main.tabBuns();
        var ingredientSauces = main.tabSauces();

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

        main.clickSauces(ingredientSauces);
        main.checkSauces(ingredientSauces);
    }
    @Test
    @Description("Проверка перехода к разделу «Начинки»")
    public void goFillingsSection() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        var ingredientFillings = main.tabFillings();

        main.clickFillings(ingredientFillings);
        main.checkFillings(ingredientFillings);
    }
    @After
    public void deleteUser() {
        if (accessToken != null) {
            check.userDelete(accessToken);
        }
    }
}

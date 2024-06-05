package pages;

import org.example.EnvConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;
    private final By buttonLoginAcc = By.cssSelector("button.button_button_size_large__G21Vg");
    private final By buttonPersonalAcc = By.linkText("Личный Кабинет");
    private final By pageBurger = By.cssSelector("a[class^='BurgerIngredient']");
    private final By clickLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By ingredientSauces = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private final By ingredientBuns = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private final By ingredientFillings = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
    private final By waitingShellfish = By.xpath(".//img[@alt='Мясо бессмертных моллюсков Protostomia']");
    private final By buttonArea = By.linkText("Личный Кабинет");
    private final By buttonRegistration = By.xpath("//a[@href='/register']");
    private final By loginButton = By.xpath("//a[@href='/login']");
    private final By buttonRestore = By.xpath(".//a[text()='Восстановить пароль']");
    private String accessToken;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(EnvConfig.BASE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonLoginAcc));

    }
    public LoginPage clickButtonLoginAcc() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonLoginAcc));
        driver.findElement(buttonLoginAcc).click();
        return new LoginPage(driver);
    }
    public void getToken() {
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();    //Получаем токен
        this.accessToken = localStorage.getItem("accessToken");
        System.out.println("Access Token: " + this.accessToken);
    }
    public ProfilePage clickButtonPersonalAcc() {
        driver.findElement(buttonPersonalAcc).click();
        return new ProfilePage(driver);
    }

    public void waitLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(pageBurger));
    }

    public void constructorCheck() {
        boolean isInConstructor = driver.findElement(pageBurger).isDisplayed();
        Assert.assertTrue("Пользователь не перешел к конструктору.", isInConstructor);
    }

    public void goConstructor() {
        driver.findElement(By.xpath(".//p[text()='Конструктор']")).click(); //Кликаем для перехода в конструктор
    }
    public void navigationLogoCheck() {
        boolean isInConstructor = driver.findElement(pageBurger).isDisplayed();
        Assert.assertTrue("Пользователь не перешел в конструктор через лого.", isInConstructor);
    }

    public void navigationLogo() {
        driver.findElement(clickLogo).click();   //Кликаем на лого для перехода
    }
    public By tabSauces() {
        var ingredientTab = ingredientSauces;
        return ingredientTab;
    }

    public By tabBuns() {
        var ingredientTab = ingredientBuns;
        return ingredientTab;
    }
    public void clickBuns(By ingredientTab) {
        driver.findElement(ingredientTab).click();
    }
    public void checkBuns(By ingredientBuns) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(ingredientBuns, "class", "current"));  //Дожитается появления стиля

        String actualClassAttribute = driver.findElement(ingredientBuns).getAttribute("class"); //Проверяем, что стиль появился

        assertTrue(actualClassAttribute.contains("current"));
    }
    public void clickSauces(By ingredientSauces) {
        driver.findElement(ingredientSauces).click();
    }
    public void checkSauces(By ingredientSauces) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(ingredientSauces, "class", "current"));  //Дожитается появления стиля

        String actualClassAttribute = driver.findElement(ingredientSauces).getAttribute("class"); //Проверяем, что стиль появился

        assertTrue(actualClassAttribute.contains("current"));
    }
    public By tabFillings() {
        var ingredientTab = ingredientFillings;
        return ingredientTab;
    }
    public void clickFillings(By ingredientFillings) {
        driver.findElement(ingredientFillings).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(waitingShellfish));

    }
    public void checkFillings(By ingredientFillings) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(ingredientFillings, "class", "current"));  //Дожитается появления стиля

        String actualClassAttribute = driver.findElement(ingredientFillings).getAttribute("class"); //Проверяем, что стиль появился

        assertTrue(actualClassAttribute.contains("current"));
    }
    public void checkLog() {
        boolean isLoggedIn = driver.findElement(pageBurger).isDisplayed();
        Assert.assertTrue("Пользователь не вошел в систему успешно.", isLoggedIn);
    }
    public LoginPage clickButtonArea() {
        driver.findElement(buttonArea).click();
        return new LoginPage(driver);
    }
    public void clickButtonRegistration() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonRegistration));
        driver.findElement(buttonRegistration).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));

    }
    public LoginPage clickButtonToComeIn() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }
    public void clickRestorePassword() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonRestore));
        driver.findElement(buttonRestore).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));

    }
    public void clickButtonPersonalArea() {
        driver.findElement(buttonPersonalAcc).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonRestore));

    }
    public RegistrationPage clickRegistration() {
        driver.findElement(buttonRestore).click();
        return new RegistrationPage(driver);

    }
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

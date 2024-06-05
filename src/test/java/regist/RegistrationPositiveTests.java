package regist;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.given;

public class RegistrationTests {
    private WebDriver driver;
    private String accessToken;
    @Before
    public void initDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
      //  RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
     //   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
   // @After
   // public void closePage(){
    //    driver.quit();

  //  }
    @After
    public void deleteUser() {
        System.out.println("deleteUser method called");
        System.out.println("Access Token: " + accessToken);
        if (accessToken != null) {
            given().log().all()
                    .contentType(ContentType.JSON)
                    .header("Authorization", accessToken)
                    .when()
                    .delete("https://qa-mesto.praktikum-services.ru/api/auth/user")
                    .then().log().all()
                    .assertThat()
                    .statusCode(HttpURLConnection.HTTP_ACCEPTED);
        } else {
            System.out.println("No access token found. Skipping user deletion.");
        }
    }
//    @Test
//    @Description("Проверка успешной регистрации")
//    public void userRegistration(){
//        driver.get("https://stellarburgers.nomoreparties.site/");
//
//        driver.findElement(By.linkText("Личный Кабинет")).click();
//        driver.findElement(By.className("Auth_link__1fOlj")).click();
//        driver.findElement(By.cssSelector(("input[name='name']"))).sendKeys("Дима9");
//        driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input[@type='text']")).sendKeys("Dmitrii9@yandex.ru");
//        driver.findElement(By.cssSelector(("input[name='Пароль']"))).sendKeys("pass1234");
//        driver.findElement(By.xpath("//button[text()='Зарегистрироваться']")).click();
//
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Войти']")));
//
//        assert driver.findElement(By.xpath("//button[text()='Войти']")).isDisplayed();
//
//    }
    @Test
    @Description("Логинимся чтобы получить токен")
    public void getItToken(){
        driver.get("https://stellarburgers.nomoreparties.site/");

        driver.findElement(By.xpath("//button[text()='Войти в аккаунт']")).click();
        driver.findElement(By.cssSelector(("input[name='name']"))).sendKeys("Dmitrii9@yandex.ru");
        driver.findElement(By.cssSelector(("input[name='Пароль']"))).sendKeys("pass1234");
        driver.findElement(By.xpath("//button[text()='Войти']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class^='BurgerIngredient']")));

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
         accessToken = localStorage.getItem("accessToken");
        System.out.println("Access Token: " + accessToken);

        assertTrue("Access token should not be null", accessToken != null);
    }
}

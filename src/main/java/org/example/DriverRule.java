package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverRule extends ExternalResource {
    private WebDriver driver;
    @Override
    protected void before() throws Throwable{
        initDriver();
    }
    @Override
    protected void after(){
        driver.quit();
    }
    private void initDriver(){
        if ("yandex".equals(System.getProperty("browser"))){
            initYandex();
        }else{
            initGoogle();
        }
    }
    private void initGoogle(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }
    private void initYandex(){
    WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();
        var options = new ChromeOptions();
        options.setBinary(System.getProperty("webdriver.yandex.bin"));
        driver = new ChromeDriver(options);
    }
    public WebDriver getDriver(){
        return driver;
    }
}

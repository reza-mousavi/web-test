package com.lector.webtest.client;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/9/2016
 */
public class Client {

    private static final String PHANTOM_JS_PATH = "C:/java/phantomjs-2.1.1/bin/phantomjs.exe";

    public static void main(String[] args) {
        testDriver(Client::phantomJSDriver);
        testDriver(Client::htmlUnitDriver);
        //testDriver(Client::fireFoxDriver);
        testDriver(Client::chromeDriver);
    }

    private static void testDriver(Supplier<? extends WebDriver> supplier) {
        final WebDriver driver = supplier.get();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        driver.get("http://127.0.0.1:8080/greeting?name=reza");
        final String title = driver.getTitle();
        final String pageSource = driver.getPageSource();
        System.out.println("Page title is : " + title);
        System.out.println("Page source is : " + pageSource);
        driver.quit();
    }

    //

    /**
     * Chrome with user interface, it is npt required Chrome be installed on local machine.
     *By using ChromeDriverManager the driver will be downloaded automatically from
     * corresponding web-site.
     * @return Chrome driver
     */
    private static WebDriver chromeDriver() {
        ChromeDriverManager.getInstance().setup();
        final ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    //Headless driver based on Mozilla firefox Rhino
    private static WebDriver htmlUnitDriver() {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setPlatform(Platform.WIN10);
        return new HtmlUnitDriver(desiredCapabilities);
    }

    //Firefox with user interface
    private static WebDriver fireFoxDriver() {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setPlatform(Platform.WIN10);

        return new FirefoxDriver(desiredCapabilities);
    }

    //Headless driver relies on Chrome and Saffari engine
    private static WebDriver phantomJSDriver() {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setPlatform(Platform.WIN10);
        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_JS_PATH);

        return new PhantomJSDriver(desiredCapabilities);
    }
}

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

import java.util.function.Supplier;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/9/2016
 */
public class ClientTest {

    private static final String PHANTOM_JS_PATH = "C:/java/phantomjs-2.1.1/bin/phantomjs.exe";
    private static final String CHROME_PATH = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe";

    public static void main(String[] args) {
        testDriver(ClientTest::phantomJSDriver);
        testDriver(ClientTest::htmlUnitDriver);
        //testDriver(Client::fireFoxDriver);
        //testDriver(ClientTest::chromeDriver);
    }

    private static void testDriver(Supplier<? extends WebDriver> supplier) {
        final WebDriver driver = supplier.get();
        driver.get("http://127.0.0.1:8080/greeting/");
        final String title = driver.getTitle();
        final String pageSource = driver.getPageSource();
        System.out.println("Page title is : " + title);
        System.out.println("Page source is : " + pageSource);
        driver.quit();
    }

    private static WebDriver chromeDriver() {
        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver();
    }

    private static WebDriver htmlUnitDriver() {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setPlatform(Platform.WIN10);
        return new HtmlUnitDriver(desiredCapabilities);
    }

    private static WebDriver fireFoxDriver() {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setPlatform(Platform.WIN10);

        WebDriver driver = new HtmlUnitDriver();
        return new FirefoxDriver(desiredCapabilities);
    }

    private static WebDriver phantomJSDriver() {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setPlatform(Platform.WIN10);
        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_JS_PATH);

        return new PhantomJSDriver(desiredCapabilities);
    }
}

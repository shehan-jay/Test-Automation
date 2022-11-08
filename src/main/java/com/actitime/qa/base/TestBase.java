package com.actitime.qa.base;

import com.actitime.qa.util.TestUtil;
import com.actitime.qa.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties properties;

    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;

    public TestBase() {

        try {
            properties = new Properties();
            FileInputStream iprop = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/"
                    + "actitime/qa/config/config.properties");
            properties.load(iprop);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {

        String browserName = properties.getProperty("BROWSER");

        if (browserName.equals("chrome")) {
            String chromeDriverExecutablePath = properties.getProperty("CHROME_EXECUTABLE_PATH");
            System.setProperty("webdriver.chrome.driver", chromeDriverExecutablePath);
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            String firefoxDriverExecutablePath = properties.getProperty("FIREFOX_EXECUTABLE_PATH");
            System.setProperty("webdriver.gecko.driver", firefoxDriverExecutablePath);
            driver = new FirefoxDriver();
        } else if (browserName.equals("IE")) {
            // IE Driver
        }

        e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(properties.getProperty("WEB_SITE_URL"));
    }
}

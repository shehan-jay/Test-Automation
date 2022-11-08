package com.actitime.qa.testcases;

import com.actitime.qa.base.TestBase;
import com.actitime.qa.pages.HomePage;
import com.actitime.qa.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.logging.Logger;

public class HomePageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    public HomePageTest() {
        super();

    }


    @BeforeMethod
    public void setup() {
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.loging(properties.getProperty("ADMIN_USERNAME"), properties.getProperty("ADMIN_PASSWORD"));

    }


    @Test(priority = 1)
    public void homePageLogoTest() {
        SoftAssert softAssertion = new SoftAssert();
        boolean flag = homePage.validateActiTimeLogo();
        softAssertion.assertTrue(flag, "Cannot find the Logo");
        softAssertion.assertAll();
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }


}

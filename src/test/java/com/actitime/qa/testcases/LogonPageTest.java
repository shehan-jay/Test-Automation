package com.actitime.qa.testcases;

import com.actitime.qa.base.TestBase;
import com.actitime.qa.pages.HomePage;
import com.actitime.qa.pages.LoginPage;
import com.actitime.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LogonPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    String validTestDataSheetName = "ValidUsersCred";
    String invalidTestDataSheetName = "InvalidUsersCred";
    TestUtil testUtil;


    public LogonPageTest() {
        super();
    }


    @BeforeMethod
    public void setup() {
        initialization();
        loginPage = new LoginPage();

    }


    @Test(priority = 1)
    public void loginPageLogoTest() {
        SoftAssert softAssertion = new SoftAssert();
        boolean flag = loginPage.validateActiTimeLogo();
        softAssertion.assertTrue(flag);
        softAssertion.assertAll();
    }


    @DataProvider
    public Object[][] getValidLoginTestData() {
        Object data[][] = testUtil.getTestData(validTestDataSheetName);

        return data;


    }

    @DataProvider
    public Object[][] getInvalidLoginTestData() {
        Object data[][] = testUtil.getTestData(invalidTestDataSheetName);
        return data;
    }

    @Test(dataProvider = "getInvalidLoginTestData")
    public void validateInvalidUserLoginTest(String userName, String password) throws InterruptedException {

        loginPage.invalidLogging(userName, password);
        Assert.assertEquals(loginPage.validateInvalidLogin(), "Username or Password is invalid. Please try again.", "Invalid login error msg is not displayed");

    }

    @Test(priority = 2, dataProvider = "getValidLoginTestData")
    public void LoginTest(String userName, String password) {

        homePage = loginPage.loging(userName, password);
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}

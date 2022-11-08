package com.actitime.qa.testcases;

import com.actitime.qa.base.TestBase;
import com.actitime.qa.pages.HomePage;
import com.actitime.qa.pages.LoginPage;
import com.actitime.qa.pages.TimeTrackPage;
import com.actitime.qa.pages.UsersPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TimeSheetApproveRejectPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    TimeTrackPage timeTrackPage;

    public TimeSheetApproveRejectPageTest() {
        super();

    }
    public static Logger logger = Logger.getLogger(TimeSheetApproveRejectPageTest.class);

    @BeforeMethod
    public void setup() {
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.loging(properties.getProperty("ADMIN_USERNAME"), properties.getProperty("ADMIN_PASSWORD"));
        timeTrackPage = new TimeTrackPage();

    }

    @Test(priority = 1)
    public void validateRejectTimeTest() {
        SoftAssert softAssertion = new SoftAssert();
        homePage.clickOnTimeTrackLink();
        softAssertion.assertTrue(timeTrackPage.validateTimeTrackPageTitle(), "Cannot find the Time Track section page title");
        softAssertion.assertTrue(timeTrackPage.validateApproveTimeTrackNav(), "Cannot find the Approve time track Navigation panel");
        timeTrackPage.clickApproveTimeTrackPanel();


        softAssertion.assertTrue(timeTrackPage.validateApproveTimeTrackPageTitle(), "Cannot find the Approve Time Track section page title");
        softAssertion.assertTrue(timeTrackPage.validateApproveTable(), "Cannot find the Approve Time Track table");
        softAssertion.assertTrue(timeTrackPage.validateUserNameListCount(), "List of users in Approve table is zero");

        timeTrackPage.selectFirstUserCheckBox();
        timeTrackPage.clickRejectBtn();
        softAssertion.assertAll();
    }

    @Test(priority = 2)
//    @Test(dependsOnMethods={"validateRejectTimeTest"})
    public void validateRejectedTimeTestIsNotAllowedToBeRejectedAgain() throws InterruptedException {
        SoftAssert softAssertion = new SoftAssert();
        homePage.clickOnTimeTrackLink();
        timeTrackPage.clickApproveTimeTrackPanel();

        timeTrackPage.selectFirstUserCheckBox();
        timeTrackPage.clickRejectBtn();

        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = driver.switchTo().alert();
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        logger.info("Alert Message : "+ alertText);
        softAssertion.assertEquals(alertText, "This operation was not applied to the entries because their current status coincides with the operation status.", "Incorrect Alert text is displayed");
        alert.accept();

        softAssertion.assertAll();

    }

    @Test(priority = 3)
//    @Test(dependsOnMethods={"validateRejectedTimeTestIsNotAllowedToBeRejectedAgain"})
    public void validateApproveTimeTest() {
        SoftAssert softAssertion = new SoftAssert();
        homePage.clickOnTimeTrackLink();
        timeTrackPage.clickApproveTimeTrackPanel();

        driver.navigate().refresh();
        timeTrackPage.selectFirstUserCheckBox();
        timeTrackPage.clickApproveBtn();

        softAssertion.assertAll();
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }


}

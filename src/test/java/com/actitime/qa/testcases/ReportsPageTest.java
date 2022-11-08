package com.actitime.qa.testcases;

import com.actitime.qa.base.TestBase;
import com.actitime.qa.pages.HomePage;
import com.actitime.qa.pages.LoginPage;
import com.actitime.qa.pages.ReportsPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ReportsPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    ReportsPage reportsPage;

    public ReportsPageTest() {
        super();

    }


    @BeforeMethod
    public void setup() {
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.loging(properties.getProperty("ADMIN_USERNAME"), properties.getProperty("ADMIN_PASSWORD"));

        reportsPage = new ReportsPage();

    }


    @Test
    public void validateReportsTest() {
        SoftAssert softAssertion = new SoftAssert();
        homePage.clickOnReportsLink();
        softAssertion.assertTrue(reportsPage.validateReportPageTitle(), "Cannot find the Reports section page title");
        softAssertion.assertTrue(reportsPage.validateLeaveChart(), "Cannot find the Leave Chart in Reports section");

        reportsPage.clickLeaveChart();
        softAssertion.assertEquals(reportsPage.getReportName(), "Chart: Past Month's Leaves", "Incorrect report name is displayed");
        reportsPage.clickCancelBtnInCreateChart();

        reportsPage.clickAttendanceChart();
        softAssertion.assertEquals(reportsPage.getReportName(), "Scheduled vs. Worked Hours+Overtime", "Incorrect report name is displayed");
        softAssertion.assertAll();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

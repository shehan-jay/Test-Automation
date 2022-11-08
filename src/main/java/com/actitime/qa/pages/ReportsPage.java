package com.actitime.qa.pages;

import com.actitime.qa.base.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class ReportsPage extends TestBase {

    // Web Element Xpath

    @FindBy(xpath = "//*[@class='pagetitle'][text() = \"Reports Dashboard\"]")
    WebElement reportDashboardPageTitle;
    @FindBy(xpath = "//*[@id='reportConfig_119']")
    WebElement pastMonthsLeaveChartSection;
    @FindBy(xpath = "//*[@id='reportConfig_90']")
    WebElement attendanceChartSection;
    @FindBy(className = "reportName")
    WebElement reportNameInExpandedView;
    @FindBy(xpath = "//*[@id='createChartLightbox_cancelBtn']")
    WebElement cancelBtnInCreateChart;

    public static Logger logger = Logger.getLogger(ReportsPage.class);
    // Call init
    public ReportsPage() {

        PageFactory.initElements(driver, this);

    }

    // Methods
    public Boolean validateReportPageTitle() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return reportDashboardPageTitle.isDisplayed();
    }

    public Boolean validateLeaveChart() {
        return pastMonthsLeaveChartSection.isDisplayed();
    }

    public void clickLeaveChart() {
        logger.info("Clicking past Months Leave Chart Section");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        pastMonthsLeaveChartSection.click();
    }

    public String getReportName() {
        logger.info("get Report Name");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        return reportNameInExpandedView.getText().trim();
    }

    public void clickAttendanceChart() {
        logger.info("Clicking Attendance Chart Section");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        attendanceChartSection.click();
    }

    public void clickCancelBtnInCreateChart() {
        logger.info("Clicking Cancel Button");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        cancelBtnInCreateChart.click();
    }


}

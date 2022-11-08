package com.actitime.qa.pages;

import com.actitime.qa.base.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TimeTrackPage extends TestBase {
    @FindBy(xpath = "//td[@class='pagetitle'][text() = \"Enter Time-Track\"]")
    WebElement timeTrackPageTitle;
    @FindBy(linkText = "Approve Time-Track")
    WebElement approveTimeTrackNav;
    // Start Approve time page
    @FindBy(xpath = "//*[@class='pagetitle'][text() = 'Approve Time-Track']")
    WebElement approveTimeTrackPageTitle;
    @FindBy(id = "approveButton")
    WebElement approveBtn;
    @FindBy(id = "rejectButton")
    WebElement rejectBtn;
    @FindBy(id = "approveTTTable")
    WebElement approveTable;
    @FindBy(className = "userNameInfo")
    List<WebElement> userNameListInApproveTable;
    @FindBy(className = "noRecordsRow")
    WebElement unoRecordsRowInApproveTable;
    @FindBy(xpath = "//table[@id='approveTTTable']//tbody[@class='data']//td//input[@type='checkbox']")
    WebElement approveTableFirstUserCheckBox;

    public static Logger logger = Logger.getLogger(TimeTrackPage.class);
    // Call init
    public TimeTrackPage() {

        PageFactory.initElements(driver, this);

    }

    // Methods
    public Boolean validateTimeTrackPageTitle() {
        return timeTrackPageTitle.isDisplayed();
    }

    public Boolean validateApproveTimeTrackNav() {
        return approveTimeTrackNav.isDisplayed();
    }

    public void clickApproveTimeTrackPanel() {
        logger.info("Clicking Approve Time Track");
        approveTimeTrackNav.click();
    }

    // Methods
    public Boolean validateApproveTimeTrackPageTitle() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return approveTimeTrackPageTitle.isDisplayed();
    }

    public Boolean validateApproveTable() {
        return approveTable.isDisplayed();
    }

    public void clickApproveBtn() {
        logger.info("Clicking Approve Button");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        approveBtn.click();
    }

    public void clickRejectBtn() {
        logger.info("Clicking Reject Button");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        rejectBtn.click();
    }

    public void selectFirstUserCheckBox() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        logger.info("Getting users count in Approve table");
        if (userNameListInApproveTable.size() > 0) {
            approveTableFirstUserCheckBox.click();
        } else {
            logger.info("----List of users in Approve table is zero----");
            System.exit(0);
        }
    }

    public Boolean validateUserNameListCount() {
        logger.info("Getting users count in Approve table");
        if (userNameListInApproveTable.size() == 0) {
            return false;
        } else
            return true;
    }


}

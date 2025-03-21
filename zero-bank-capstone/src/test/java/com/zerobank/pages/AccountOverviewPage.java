package com.zerobank.pages;

import java.time.Duration;

import com.zerobank.utils.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AccountOverviewPage {
    private WebDriver driver;
    private WebDriverWait wait;
    ExtentTest extendTest;

    public AccountOverviewPage() {
        // TODO Auto-generated constructor stub
    }

    public AccountOverviewPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        this.extendTest = test;
    }

    private By signIn = By.id("signin_button");
    private By username = By.xpath("//input[@id='user_login']");
    private By password = By.id("user_password");
    private By signInButton = By.name("submit");
    private By onlineBanking = By.xpath("//strong[text()='Online Banking']");
    private By accountPage = By.id("account_summary_link");
    private By savings = By
            .xpath("//table[@class='table']/tbody/tr/td/a[contains(@href,'/bank/account-activity.html?accountId=3')]");
    private By checkings = By
            .xpath("//table[@class='table']/tbody/tr/td/a[@href='/bank/account-activity.html?accountId=2']");
    private By creditCards = By
            .xpath("//table[@class='table']/tbody/tr/td/a[@href='/bank/account-activity.html?accountId=5']");
    private By loans = By
            .xpath("//table[@class='table']/tbody/tr/td/a[@href='/bank/account-activity.html?accountId=4']");

    public boolean validateTitle() {
        String curTitle = driver.getTitle();

        boolean actResult;

        try {
            curTitle.equals("Zero - Personal Banking - Loans - Credit Cards");
            actResult = true;
            ReportUtil.generateReport(driver, extendTest, Status.PASS, "Current Title:" + curTitle);
        } catch (Exception e) {
            actResult = false;
            ReportUtil.generateReport(driver, extendTest, Status.FAIL, "Invalid website");
        }
        return actResult;
    }

    public void signIn() {
        driver.findElement(signIn).click();
        driver.findElement(username).sendKeys("username");
        driver.findElement(password).sendKeys("password");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(signInButton));
        driver.findElement(signInButton).click();

    }

    public void back() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.navigate().back();
    }

    public void accountDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(onlineBanking));
        driver.findElement(onlineBanking).click();
        driver.findElement(accountPage).click();

    }

    public boolean validateAccountTypes() {

        boolean actResult = true;
        try {
            driver.findElement(savings).isDisplayed();
            driver.findElement(checkings).isDisplayed();
            driver.findElement(creditCards).isDisplayed();
            driver.findElement(loans).isDisplayed();
            ReportUtil.generateReport(driver, extendTest, Status.PASS, "All types of accounts type of displayed");
        } catch (Exception e) {
            actResult = false;
            ReportUtil.generateReport(driver, extendTest, Status.FAIL, "All type of accounts type are displayed");
        }
        return actResult;
    }

}
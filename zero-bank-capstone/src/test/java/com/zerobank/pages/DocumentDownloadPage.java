package com.zerobank.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zerobank.utils.DriverBase;
import com.zerobank.utils.ReportUtil;

public class DocumentDownloadPage {
    private WebDriver driver;
    private WebDriverWait wait;
    ExtentTest extentTest;

    public DocumentDownloadPage() {
        // TODO Auto-generated constructor stub
    }

    public DocumentDownloadPage(WebDriver driver, ExtentTest test) {
        this.driver = DriverBase.driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.extentTest = test;
    }

    private By signIn = By.id("signin_button");
    private By username = By.xpath("//input[@id='user_login']");
    private By password = By.id("user_password");
    private By signInButton = By.name("submit");
    private By onlineBanking = By.xpath("//strong[text()='Online Banking']");
    private By onlineStatementSection = By.xpath("//span[text()='Online Statements']");
    private By accountType = By.xpath("//div[@class='controls']/select/option[@value='5']");
    private By choosingDate = By
            .xpath("//div[@id=\"online_statements_for_account\"]/div/div/div[2]/ul/li[2]/a[text()='2011']");
    private By choosingStatement = By.xpath(
            "//div[@id=\"online_statements_for_account\"]/div/div/div[4]/div[@id='os_2012']/table/tbody/tr/td/a");

    public void signIn(String uname, String pass) {
        driver.findElement(signIn).click();
        driver.findElement(username).sendKeys(uname);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(signInButton).click();
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(onlineBanking));
    }

    public void statementPage() {
        driver.findElement(onlineBanking).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(onlineStatementSection));
        driver.findElement(onlineStatementSection).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountType));
    }

    public void choosingDetails() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountType));
        driver.findElement(accountType).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(choosingDate));
        driver.findElement(choosingDate).click();
    }

    public void clickingDownload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(choosingStatement));
        driver.findElement(choosingStatement).click();
    }

    public void downloadingFile() throws Exception {
        try {
            Robot robot = new Robot();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

            // Press 'Tab' to focus on the 'Keep' button
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

            // Press 'Enter' to confirm 'Keep'
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

            ReportUtil.generateReport(driver, extentTest, Status.PASS, "Succesffully Downloading the file. ");
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        } catch (AWTException e) {
            System.out.println("Error handling download permission: " + e.getMessage());
            ReportUtil.generateReport(driver, extentTest, Status.FAIL, "Not downloading the file.");

        }
    }

    public boolean isPdfFile() {

        File file = new File("C:\\Users\\HP\\Downloads\\8534567-01-10-12.pdf");
        String fileName = file.getName();
        return fileName.endsWith(".pdf");
    }

}
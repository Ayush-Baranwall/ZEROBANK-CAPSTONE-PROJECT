package com.zerobank.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import com.zerobank.utils.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class UserLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    ExtentTest extendTest;

    public UserLoginPage() {

    }

    public UserLoginPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        this.extendTest = test;
    }

    private By signIn = By.xpath("//button[@id='signin_button']");
    private By username = By.xpath("//input[@id='user_login']");
    private By password = By.xpath("//input[@id='user_password']");
    private By button = By.xpath("//input[@type='submit']");
    private By onlineBanking = By.xpath("//div[@class='span12']/div/ul/li[2]/div/strong[text()='Online Banking']");
    private By accSummary = By.xpath("//span[@id=\"account_summary_link\"]");

    public boolean logIn() {
        driver.findElement(signIn).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return true;

    }

    public boolean isOnLoginPage() {
        boolean actReasult=true;
        try {

            boolean UsernameVisible = driver.findElement(username).isDisplayed();
            boolean PasswordVisible = driver.findElement(password).isDisplayed();
            boolean LoginVisible = driver.findElement(button).isDisplayed();
            ReportUtil.generateReport(driver, extendTest, Status.PASS, "Login is successful");
        } catch (NoSuchElementException e) {
            actReasult= false;
            ReportUtil.generateReport(driver, extendTest, Status.FAIL, "Login is unsuccessful");
        }
        return actReasult;
    }

    public boolean errorMessage() {
        boolean actResult = true;
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-error']")));
            ReportUtil.generateReport(driver, extendTest, Status.PASS,
                    "Login and/or password are wrong - Error message displayed successfully");
        } catch (Exception te) {
            actResult = false;
            ReportUtil.generateReport(driver, extendTest, Status.FAIL, "Error message display failed");
        }
        return actResult;
    }

    public boolean credentials(String uname, String pass) {
        driver.findElement(username).sendKeys(uname);
        driver.findElement(password).sendKeys(pass);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return true;
    }

    public boolean signIn() {
        boolean actReasult=true;
        try {
            WebElement goingBack=driver.findElement(button);
            goingBack.click();
            driver.navigate().back();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            ReportUtil.generateReport(driver, extendTest, Status.PASS, "Login Successfully");

        }catch (Exception e) {

            actReasult= false;
            ReportUtil.generateReport(driver, extendTest, Status.FAIL,"Login Failed");
        }
        return actReasult;
    }
    public void wrongSignIn() {
        try {
            driver.findElement(button).click();
            ReportUtil.generateReport(driver, extendTest, Status.PASS, "Clicked the login button for wrong sign-in.");
        } catch (Exception e) {
            ReportUtil.generateReport(driver, extendTest, Status.FAIL, "Login button click failed: " );
        }
    }

    public boolean accountSummary() {
        boolean actReasult=true;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(onlineBanking)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(accSummary)).click();
            ReportUtil.generateReport(driver, extendTest, Status.PASS, "Account summary found");
        } catch (NoSuchElementException e) {
            actReasult=false;
            ReportUtil.generateReport(driver, extendTest, Status.FAIL, "Account summary element not found.");
        }
        return actReasult;
    }

}
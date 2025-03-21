package com.zerobank.pages;

import java.time.Duration;

import com.zerobank.utils.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zerobank.utils.DriverBase;

public class BillPaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;
    ExtentTest extentTest;

    public BillPaymentPage() {
        // TODO Auto-generated constructor stub
    }

    public BillPaymentPage(WebDriver driver, ExtentTest test) {
        this.driver = DriverBase.driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        this.extentTest = test;
    }

    private By BillPayment = By.xpath("//a[text()='Pay Bills']");
    private By choosePayee = By.xpath("//select[@id=\"sp_payee\"]/option[text()='Bank of America']");
    private By amountField = By.id("sp_amount");
    private By dateField = By.id("sp_date");
    private By descriptionField = By.id("sp_description");
    private By payButton = By.id("pay_saved_payees");
    private By successfulMessage = By.xpath("//span[text()='The payment was successfully submitted.']");
    private By newPayeeSection = By.xpath("//a[contains(text(),'Add New Payee')]");
    private By payeeName = By.id("np_new_payee_name");
    private By payeeAddress = By.id("np_new_payee_address");
    private By payeeAcount = By.id("np_new_payee_account");
    private By addButton=By.id("add_new_payee");
    private By payeeDetails=By.id("np_new_payee_details");
    private By successfulAddingNewPayeeMessage=By.id("alert_content");

    public void BillPaymentSection() {
        driver.findElement(BillPayment).click();
    }

    public void addingMoney(String money, String date) {
        driver.findElement(amountField).sendKeys(money);
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(descriptionField).sendKeys("You successfully submitted for your transaction");
    }

    public void payButton() {
        driver.findElement(payButton).click();
    }

    //	public Boolean validateSuccessfulMessage(String message) {
//		boolean actResult=true;
//		try {
//			driver.findElement(successfulMessage).sendKeys(message);
//
//			Assert.assertEquals(actResult, "The payment was successfully submitted.");
//			ReportUtil.generateReport(driver, extentTest, Status.PASS, message);
//		} catch (Exception e) {
//			actResult=false;
//			ReportUtil.generateReport(driver, extentTest, Status.FAIL, message);
//		}
//		return actResult;
//	}
    public Boolean validateSuccessfulMessage(String msg) {
        boolean actResult = true;
        try {
            String message = driver.findElement(successfulMessage).getText();
            System.out.println(message);
            Assert.assertEquals(message, msg);
            ReportUtil.generateReport(driver, extentTest, Status.PASS, msg);
        } catch (Exception e) {
            actResult = false;
            ReportUtil.generateReport(driver, extentTest, Status.FAIL, msg);
        }
        return actResult;
    }

    public Boolean validateScheduleMessage(String msg) {
        boolean actReasult = true;
        try {
            String message = driver.findElement(successfulMessage).getText();
            Assert.assertTrue(actReasult);
            ReportUtil.generateReport(driver, extentTest, Status.PASS, msg);
        } catch (Exception e) {
            actReasult = false;
            ReportUtil.generateReport(driver, extentTest, Status.FAIL, msg);
        }
        return actReasult;
    }

    //	public Boolean validateSkippingAmount(String msg) {
//		boolean actReasult=true;
//		try {
//
//			String message=driver.findElement(successfulMessage).getText();
//			//Assert.assertTrue(actReasult);
//			Assert.assertEquals(actReasult, message);
//			ReportUtil.generateReport(driver, extentTest, Status.PASS, msg);
//		}catch (Exception e) {
//			actReasult=false;
//			ReportUtil.generateReport(driver, extentTest, Status.FAIL, msg);
//		}
//		return actReasult;
//	}
    public Boolean validateSkippingAmount(String msg) {
        boolean actReasult = true;
        try {

            // String message=driver.findElement(successfulMessage).getText();
            WebElement amount = driver.findElement(By.id("sp_amount"));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String message1 = (String) js.executeScript("return arguments[0].validationMessage;", amount);
            System.out.println(message1);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(successfulMessage));
            // Assert.assertTrue(actReasult);
            Assert.assertEquals(message1, msg);

            ReportUtil.generateReport(driver, extentTest, Status.PASS, msg);
        } catch (Exception e) {
            actReasult = false;
            ReportUtil.generateReport(driver, extentTest, Status.FAIL, msg);
        }
        return actReasult;
    }

    public void newPayee() {
        driver.findElement(newPayeeSection).click();

    }

    public void selectPayee() {
        driver.findElement(choosePayee).click();
    }

    public void addingPayeeDetails(String name, String address, String account) {
        driver.findElement(payeeName).sendKeys(name);
        driver.findElement(payeeAddress).sendKeys(address);
        driver.findElement(payeeAcount).sendKeys(account);
    }
    public void addPayee() {
        driver.findElement(addButton).click();
    }
    public boolean validateAddingPayeeSuccessfully(String message) {
        boolean actReasult=true;
        try {
            String notification=driver.findElement(successfulAddingNewPayeeMessage).getText();
            Assert.assertTrue(actReasult);
            ReportUtil.generateReport(driver, extentTest, Status.PASS, message);
        } catch (Exception e) {
            actReasult=false;
            ReportUtil.generateReport(driver, extentTest, Status.FAIL, message);
        }
        return actReasult;

    }

    public void checkingWithMissingDetails(String name, String address, String accountNo,String details) {
        driver.findElement(payeeName).sendKeys(name);
        driver.findElement(payeeAddress).sendKeys(address);
        driver.findElement(payeeAcount).sendKeys(accountNo);
        driver.findElement(payeeDetails).sendKeys(details);
    }
}
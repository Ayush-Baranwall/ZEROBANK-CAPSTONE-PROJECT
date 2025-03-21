package com.zerobank.steps;

import com.zerobank.pages.AccountOverviewPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.zerobank.pages.BillPaymentPage;
import com.zerobank.utils.DriverBase;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BillPaymentSteps {
    WebDriver driver = DriverBase.driver;
    ExtentTest test = Hooks.test;
    AccountOverviewPage summaryPage;
    BillPaymentPage billPage;

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        summaryPage = new AccountOverviewPage(driver, test);
        summaryPage.signIn();
        summaryPage.back();
        summaryPage.accountDetails();
    }

    @When("the user navigates to the bill payment page")
    public void the_user_navigates_to_the_bill_payment_page() {
        try {
            billPage = new BillPaymentPage(driver, test);
            billPage.BillPaymentSection();
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }

    @And("selects a payee")
    public void selects_a_payee() {
        try {
            billPage.selectPayee();
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }

    @Then("enters {string} as amount and {string}")
    public void enters_as_amount_and(String money, String date) {
        try {
            billPage.addingMoney(money, date);
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }

    @And("clicks on the pay button")
    public void clicks_on_the_pay_button() {
        try {
            billPage.payButton();
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }

    @Then("Success message {string} is displayed")
    public void success_message_is_displayed(String message) {
//	    boolean validate=billPage.validateSuccessfulMessage(message);
//	    Assert.assertTrue(validate);
        try {
            boolean validate = billPage.validateSuccessfulMessage(message);
            Assert.assertTrue(validate);
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }

    @Then("message {string} is displayed")
    public void message_is_displayed(String message) {
        try {
            boolean validate = billPage.validateScheduleMessage(message);
            Assert.assertTrue(validate);
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }

    @Then("Error message {string} should be displayed")
    public void error_message_should_be_displayed(String message) {
        try {
            boolean validate = billPage.validateSkippingAmount(message);
            Assert.assertTrue(validate);
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }
    @Then("add new payee")
    public void add_new_payee() {
        try {
            billPage.newPayee();
        } catch (Exception e) {
            Assert.fail("Error occured"+ e.getMessage());
        }
    }
    @Then("add name={string},address={string},accountNo={string}")
    public void add_name_address_account_no(String name, String address, String account) {
        try {
            billPage.addingPayeeDetails(name, address, account);
        }catch (Exception e) {
            Assert.fail("Error occured"+ e.getMessage());
        }
    }

    @Then("clicks on the add button")
    public void clicks_on_the_add_button() {
        try {
            billPage.addPayee();
        } catch (Exception e) {
            Assert.fail("Error occured"+ e.getMessage());
        }
    }

    @Then("Successful message {string} displayed")
    public void successful_message_displayed(String msg) {
        try {
            boolean validate=billPage.validateAddingPayeeSuccessfully(msg);
            Assert.assertTrue(validate);
        } catch (Exception e) {
            Assert.fail("Error occured"+e.getMessage());
        }
    }

    @Then("add the {string},{string},{string},{string}")
    public void add_the(String name, String address, String accountNo,String details) {
        try {
            billPage.checkingWithMissingDetails(name, address, accountNo, details);
        } catch (Exception e) {
            Assert.fail("Error occured"+e.getMessage());
        }
    }
}
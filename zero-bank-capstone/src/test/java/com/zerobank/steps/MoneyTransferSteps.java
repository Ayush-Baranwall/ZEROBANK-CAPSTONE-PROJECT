package com.zerobank.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.zerobank.pages.AccountOverviewPage;
import com.zerobank.pages.MoneyTransferPage;
import com.zerobank.utils.DriverBase;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoneyTransferSteps {
    WebDriver driver = DriverBase.driver;
    ExtentTest test = Hooks.test;
    AccountOverviewPage summaryPage;
    MoneyTransferPage transferPage;

/*
	@Given("the user is logged in")
	public void the_user_is_logged_in() {
		summaryPage = new AccountOverviewPage(driver, test);
		summaryPage.signIn();
		summaryPage.back();
		summaryPage.accountDetails();
	}
*/

    @When("the user navigates to the fund transfer page")
    public void the_user_navigates_to_the_fund_transfer_page() {
        transferPage = new MoneyTransferPage(driver, test);
        boolean actResult = transferPage.validateTransferFundSection();
        Assert.assertTrue(actResult);
    }

    @And("selects the sender and the reciever")
    public void selects_the_sender_and_the_reciever() {
        transferPage.selectingAccountType();
    }

    @Then("a success message should be displayed")
    public void a_success_message_should_be_displayed() {
        transferPage = new MoneyTransferPage(driver, test);
        boolean actResult = transferPage.validateSuccessfulPayment();
        Assert.assertTrue(actResult);
    }

    @When("enters the {string} and click submit")
    public void enters_the_and_click_submit(String string) {
        transferPage.amountGiven(string);
    }

    @Then("it should show {string} message")
    public void it_should_show_message(String message) {
        boolean isSuccessMessageDisplayed = transferPage.amountGiven(message);
        Assert.assertTrue(isSuccessMessageDisplayed);

    }
}
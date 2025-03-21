package com.zerobank.steps;

import com.zerobank.pages.AccountOverviewPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.zerobank.utils.DriverBase;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountSummaryStepDefinitions {
    WebDriver driver = DriverBase.driver;
    ExtentTest test = Hooks.test;
    AccountOverviewPage summaryPage;

    @Given("User is logged in")
    public void user_is_logged_in() {
        try {
            summaryPage=new AccountOverviewPage(driver, test);
            summaryPage.signIn();
            summaryPage.back();
            boolean actReasult=summaryPage.validateTitle();
            Assert.assertTrue(actReasult);
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }
    }

    @When("User navigates to the account summary page")
    public void user_navigates_to_the_account_summary_page() {
        try {
            summaryPage.accountDetails();
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }
    }

    @Then("The account details should be displayed")
    public void the_account_details_should_be_displayed() {
        try {
            System.out.println("Account Summary page is being displayed");
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }

    }

    @And("The account balances should be correct")
    public void the_account_balances_should_be_correct() {
        try {
            summaryPage=new AccountOverviewPage(driver, test);
            boolean actReasult=summaryPage.validateAccountTypes();
            Assert.assertTrue(actReasult);
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }
    }
}

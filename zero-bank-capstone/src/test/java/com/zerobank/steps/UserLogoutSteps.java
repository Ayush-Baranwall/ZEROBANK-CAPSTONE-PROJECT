package com.zerobank.steps;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.zerobank.pages.DocumentDownloadPage;
import com.zerobank.pages.UserLogoutPage;
import com.zerobank.utils.DriverBase;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class UserLogoutSteps {

    WebDriver driver = DriverBase.driver;
    ExtentTest test = Hooks.test;
    DocumentDownloadPage downloadingPage;
    UserLogoutPage logoutPage;

    @Given("The user is logged into the Zero Bank platform with {string} and {string}")
    public void the_user_is_logged_into_the_zero_bank_platform_with_and(String username, String password) {
        try {
            downloadingPage = new DocumentDownloadPage(driver, test);
            downloadingPage.signIn(username, password);
        } catch (Exception e) {
            Assert.fail("Error occured: " + e.getMessage());
        }
    }

    @When("The user clicks on the Logout button")
    public void the_user_clicks_on_the_logout_button() {
        try {
            logoutPage = new UserLogoutPage(driver, test);
            logoutPage.logout();
        } catch (Exception e) {
            Assert.fail("Error occured: " + e.getMessage());
        }
    }

    @Then("The user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        try {
            boolean validate = logoutPage.validateSignInPage();
            Assert.assertTrue(validate);
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }

    @Given("The user logs out of the application")
    public void the_user_logs_out_of_the_application() {
        try {
            logoutPage=new UserLogoutPage(driver, test);
            logoutPage.logout();
        } catch (Exception e) {
            Assert.fail("Error occured: " + e.getMessage());
        }
    }

    @When("The user clicks the Back button in the browser")
    public void the_user_clicks_the_back_button_in_the_browser() {
        try {
            logoutPage.navigatingBack();
        } catch (Exception e) {
            Assert.fail("Error occured: " + e.getMessage());
        }
    }

    @Then("The user should not be able to access the previous page")
    public void the_user_should_not_be_able_to_access_the_previous_page() {
        try {
            boolean validate = logoutPage.validateSignInPage();
            Assert.assertTrue(validate);
        } catch (Exception e) {
            Assert.fail("Error occured" + e.getMessage());
        }
    }

}
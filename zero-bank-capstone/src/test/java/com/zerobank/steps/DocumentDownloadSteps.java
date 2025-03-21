package com.zerobank.steps;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.zerobank.pages.DocumentDownloadPage;
import com.zerobank.utils.DriverBase;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DocumentDownloadSteps {
    WebDriver driver= DriverBase.driver;
    ExtentTest test=Hooks.test;
    DocumentDownloadPage downloadingPage;

    @Given("user login with {string} and {string}")
    public void user_login_with_and(String username, String password) {
        try {
            downloadingPage=new DocumentDownloadPage(driver, test);
            downloadingPage.signIn(username, password);
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }
    }

    @When("user navigate to Statements & Documents")
    public void user_navigate_to_statements_documents() {
        try {
            downloadingPage.statementPage();
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }
    }

    @Then("user select an account and a date range")
    public void user_select_an_account_and_a_date_range() {
        try {
            downloadingPage.choosingDetails();
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }
    }

    @And("click on Download PDF button")
    public void click_on_download_pdf_button() {
        try {
            downloadingPage.clickingDownload();
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }
    }

    @Then("user clicks on Keep in the download permission")
    public void user_clicks_on_keep_in_the_download_permission() throws Exception{
        try {
            downloadingPage.downloadingFile();
        } catch (Exception e) {
            Assert.fail("Error occurred while handling download permission: " + e.getMessage());
        }
    }

    @Then("the user should verify the downloaded PDF file in the download section")
    public void the_user_should_verify_the_downloaded_pdf_file_in_the_download_section() {
        try {
            boolean validate=downloadingPage.isPdfFile();
            Assert.assertTrue(validate);
        } catch (Exception e) {
            Assert.fail("Error occured: "+e.getMessage());
        }
    }


}
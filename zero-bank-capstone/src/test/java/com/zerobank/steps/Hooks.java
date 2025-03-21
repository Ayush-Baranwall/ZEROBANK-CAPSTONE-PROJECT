package com.zerobank.steps;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.zerobank.utils.DriverBase;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks extends DriverBase {
    static WebDriver driver = DriverBase.driver;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeAll()
    public static void beforeAll() {
        try {
            sparkReporter = new ExtentSparkReporter(
                    System.getProperty("user.dir") + "/zero-bank-capstone/reports/myReport1.html");
            sparkReporter.config().setDocumentTitle("Selenium Project");
            sparkReporter.config().setReportName("Regression Testing");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester Name", "John");
            extent.setSystemInfo("Browser Name", "Chrome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll()
    public static void afterAll() {
        extent.flush();
    }

    @Before
    public void initSteps(Scenario scenario) {
        System.out.println("Browser is launching...");
        test = extent.createTest(scenario.getName());
        getBrowser();
    }

    @After
    public void teardown() {
        System.out.println("Browser is closing...");
        DriverBase.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        DriverBase.driver.quit();
    }
}
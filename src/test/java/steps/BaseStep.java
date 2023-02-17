package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.FrontendTestingHomePage;
import utils.Driver;
import utils.DropdownHandler;
import utils.Waiter;

public class BaseStep {

    WebDriver driver;
    FrontendTestingHomePage frontendTestingHomePage;
    BasePage basePage;

    @Before
    public void setup(){
        driver = Driver.getDriver();
        frontendTestingHomePage = new FrontendTestingHomePage();
        basePage = new BasePage();
    }

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user moves to Practices header dropdown")
    public void userMovesToHeaderDropdown() {
       basePage.practiceDropdownButton.click();
    }

    @And("user clicks on {string} header dropdown option")
    public void userClicksOnHeaderDropdownOption(String option) {
        DropdownHandler.clickOnDropdownOption(basePage.practiceDropdownButton, basePage.practiceDropdownMenu, option);
    }

    @Then("user should be navigated to {string}")
    public void userShouldBeNavigatedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("user clicks on {string} card")
    public void userClicksOnCard(String card) {
        frontendTestingHomePage.clickOnCard(card);
    }
}

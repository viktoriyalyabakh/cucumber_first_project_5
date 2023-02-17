package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.FrontendTestingHomePage;
import pages.PaginationPage;
import utils.Driver;
import utils.Waiter;

public class PaginationStep {

    WebDriver driver;
    FrontendTestingHomePage frontendTestingHomePage;
    PaginationPage paginationPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        frontendTestingHomePage = new FrontendTestingHomePage();
        paginationPage = new PaginationPage();

    }

    @And("user should see {string} heading")
    public void userShouldSeeHeading(String heading) {
        switch (heading) {
            case "Pagination":
                driver.get(driver.getCurrentUrl());
                Assert.assertEquals(paginationPage.mainHeading.getText(), heading);
                break;
            case "World City Populations 2022":
                Assert.assertEquals(paginationPage.subHeading.getText(), heading);
                break;
            default:
                throw new NotFoundException();
        }
    }

    @And("user should see {string} paragraph")
    public void userShouldSeeParagraph(String paragraph) {
        Assert.assertEquals(paginationPage.paragraph.getText(), paragraph);
    }

    @And("user should see {string} button is disabled")
    public void userShouldSeeButtonIsDisabled(String button) {
        switch (button) {
            case "Previous":
                Assert.assertTrue(paginationPage.previousButton.isDisplayed());
                break;
            case "Next":
                Assert.assertTrue(paginationPage.nextButton.isDisplayed());
                break;
            default:
                throw new NotFoundException();
        }
    }

    @And("user should see {string} button is enabled")
    public void userShouldSeeButtonIsEnabled(String button) {
        switch (button) {
            case "Previous":
                Assert.assertTrue(paginationPage.previousButton.isEnabled());
                break;
            case "Next":
                Assert.assertTrue(paginationPage.nextButton.isEnabled());
                break;
            default:
                throw new NotFoundException();
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String button) {
        paginationPage.nextButton.click();
    }

    @When("user clicks on {string} button till it becomes disabled")
    public void userClicksOnButtonTillItBecomesDisabled(String button) {
        while (paginationPage.nextButton.isEnabled()){
            paginationPage.nextButton.click();
        }
    }

    @And("user should see city with info below and an image")
    public void userShouldSeeCityWithInfoBelowAndAnImage(DataTable info) {
            for (int i = 0; i < info.asList().size(); i++){
                //Waiter.pause(2);
                Assert.assertEquals(paginationPage.cityInfo.get(i).getText(), info.asList().get(i));
                System.out.println(paginationPage.cityInfo.get(i).getText());
        }
        paginationPage.nextButton.click();
    }
}

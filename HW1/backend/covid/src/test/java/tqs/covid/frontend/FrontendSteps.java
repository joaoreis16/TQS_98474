package tqs.covid.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class FrontendSteps {

    private WebDriver driver;


    @When("I want to access {string}")
    public void iNavigateTo(String url) {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1840, 1053));
    }


    @And("I click in \"By Country\" side bar button")
    public void clickSideBarButton() {
        driver.findElement(By.cssSelector(".sidebar-item:nth-child(2) .hide-menu")).click();
    }


    @And("I should see {string}")
    public void seeElement(String element) {
        assertThat(driver.findElement(By.cssSelector("h4")).getText(), containsString(element));
    }


    @Then("I should see the following message {string}")
    public void seeHTMLElement(String element) throws InterruptedException {
        Thread.sleep(2000); // wait for the data
        assertThat(driver.findElement(By.cssSelector(".test")).getText(), containsString(element));
    }


    @And("I fill the iso code input with {string} and the country name input with {string}")
    public void fillInfo(String iso, String countryname) {
        driver.findElement(By.id("form1")).click();
        driver.findElement(By.id("form1")).sendKeys(iso);
        driver.findElement(By.id("form2")).click();
        driver.findElement(By.id("form2")).sendKeys(countryname);
    }


    @And("I fill the iso code input with {string}")
    public void fillInfo(String iso) {
        driver.findElement(By.id("form1")).click();
        driver.findElement(By.id("form1")).sendKeys(iso);
    }


    @And("I click in search button")
    public void clickSearchButton() {
        driver.findElement(By.cssSelector(".btn")).click();
    }


    @And("I click in {int} button in the table")
    public void fillInfo(int number) throws InterruptedException {
        Thread.sleep(1000); // wait for the data
        driver.findElement(By.cssSelector("tr:nth-child("+ number +") .bi")).click();
    }


    @After()
    public void closeBrowser() {
        driver.quit();
    }

  
}

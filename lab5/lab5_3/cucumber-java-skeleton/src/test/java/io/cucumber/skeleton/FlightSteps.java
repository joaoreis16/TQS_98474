package io.cucumber.skeleton;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlightSteps {

    private WebDriver driver;

    @When("I want to access {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1840, 1053));
    }


    @And("I choose my departure city {string} and destination {string}")
    public void chooseCities(String departure_city, String arrival_city) {
        driver.findElement(By.name("fromPort")).click();
        {
        WebElement dropdown = driver.findElement(By.name("fromPort"));
        dropdown.findElement(By.xpath("//option[. = '"+ departure_city +"']")).click();
        }
        driver.findElement(By.name("toPort")).click();
        {
        WebElement dropdown = driver.findElement(By.name("toPort"));
        dropdown.findElement(By.xpath("//option[. = '"+ arrival_city +"']")).click();
        }
    }


    @And("I click Find Flights button")
    public void findFlight() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }


    @And("I should see flights of {string}")
    public void seeFlight(String flight) {
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), containsString(flight));
    }

    @And("I choose a flight and click Choose This Flight button")
    public void chooseFlight() {
        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
    }

    @And("I fill my personal informations: name {string}, street {string}, city {string}, state {string}")
    public void fillInfo(String name, String street, String city, String state) {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("João Reis");
        driver.findElement(By.cssSelector(".control-group:nth-child(2) > .controls")).click();
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("rua da velhice");
        driver.findElement(By.cssSelector(".control-group:nth-child(2) > .controls")).click();
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("ovar");
        driver.findElement(By.cssSelector(".control-group:nth-child(2) > .controls")).click();
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("gz");
    }


    @And("I click Purchase Flight button")
    public void purchaseFlight() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }


    @Then("I should see the following message {string}")
    public void confirmation(String confirmation) {
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), containsString(confirmation));
    }
    
    @After()
    public void closeBrowser() {
        driver.quit();
    }

  
}

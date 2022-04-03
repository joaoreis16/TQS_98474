package tqs;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;


@ExtendWith(SeleniumJupiter.class)
public class FlightsTest {

  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  private final String url = "https://blazedemo.com/";


  @FindBy(name="fromPort")
  private WebElement departure_dropdown;

  @FindBy(xpath = "//option[. = 'Philadelphia']")
  private WebElement departure_option;

  @FindBy(name="toPort")
  private WebElement arrival_dropdown;

  @FindBy(xpath = "//option[. = 'Dublin']")
  private WebElement arrival_option;

  @FindBy(css=".btn-primary")
  private WebElement button_1;

  @FindBy(css="tr:nth-child(3) .btn")
  private WebElement button_2;

  @FindBy(id="inputName")
  private WebElement input_name;

  @FindBy(css=".control-group:nth-child(2) > .controls")
  private WebElement button_3;

  @FindBy(id="address")
  private WebElement address;

  @FindBy(css=".control-group:nth-child(2) > .controls")
  private WebElement button_4;

  @FindBy(id="city")
  private WebElement city;

  @FindBy(css=".control-group:nth-child(2) > .controls")
  private WebElement button_5;

  @FindBy(id="state")
  private WebElement state;

  @FindBy(css="form")
  private WebElement button_6;

  @FindBy(css=".btn-primary")
  private WebElement button_7;



  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }




  @Test
  public void flightsTest(WebDriver driver) {

    driver.get(this.url);
    driver.manage().window().setSize(new Dimension(1840, 1053));
    PageFactory.initElements(driver, this);


    departure_option.click();

    arrival_option.click();

    button_1.click();
    button_2.click();

    input_name.click();
    input_name.sendKeys("João Reis");

    button_3.click();
    address.click();
    address.sendKeys("rua da velhice");

    button_4.click();
    city.click();
    city.sendKeys("ovar");


    button_5.click();
    state.click();
    state.sendKeys("gz");

    button_6.click();
    button_7.click();

  }
}

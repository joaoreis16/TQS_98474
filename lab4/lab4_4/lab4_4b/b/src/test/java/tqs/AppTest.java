package tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.remote.RemoteWebDriver;
import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import io.github.bonigarcia.seljup.DockerBrowser;

@ExtendWith(SeleniumJupiter.class)
public class AppTest {

  @Test
  public void flightsTest(@DockerBrowser(type = CHROME) RemoteWebDriver driver) {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1840, 1053));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
    }
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Dublin']")).click();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
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
    driver.findElement(By.cssSelector("form")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
  }
}

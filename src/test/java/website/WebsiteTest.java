package website;


import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

// Teszt osztály
@Slf4j
@SeleniumTest
@ExtendWith(LoggingExtension.class)
class WebsiteTest {
    // Teszteset

    @Test
    @DisplayName("Középső elem alatti elem kiíratás")
    void testElemkivalasztas(WebDriver driver) throws IOException {
        driver.get("http://127.0.0.1:5500/grid/index.html");
        var cell5 = driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"));
        var cell2 = driver.findElement(with(By.tagName("td")).below(cell5));
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.move(file.toPath(), Path.of("./screenshot.png"), StandardCopyOption.REPLACE_EXISTING);
        File file2 = (driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"))).getScreenshotAs(OutputType.FILE);
        Files.move(file2.toPath(), Path.of("./screenshot2.png"), StandardCopyOption.REPLACE_EXISTING);
        String result = cell2.getText();
        assertEquals("2", result);
    }


    @Test
    @DisplayName("Test setting the border of the input element")
    void testSetBorder(WebDriver webdriver){
        webdriver.get("http://127.0.0.1:5500/index.html");
        WebElement input =webdriver.findElement(By.id("field-to-validate"));
        String value = input.getText();
        if (value.equals("")) {
            ((JavascriptExecutor) webdriver).executeScript(
                    "arguments[0].style['border'] = '3px solid red'; ", input);
        }


        }


    @Test
    void testSearch(WebDriver driver) {
        // Given
        driver.get("https://www.python.org/");
        // When
        driver.findElement(By.id("id-search-field")).click();
        driver.findElement(By.id("id-search-field")).sendKeys("testing");
        driver.findElement(By.id("submit")).click();
        log.debug("Click on GO button");

        // Then
        String result = driver.findElement(By.cssSelector("h3:nth-child(2)")).getText();
        assertEquals("Results", result);
    }

    @Test
    void testPsf(WebDriver driver) {
        driver.get("https://www.python.org/");
        driver.findElement(By.linkText("PSF")).click();
        log.debug("Click on PDF menu item");
        assertEquals("Python Software Foundation", driver.getTitle());
    }



}
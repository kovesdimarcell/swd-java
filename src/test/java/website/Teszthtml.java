package website;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SeleniumTest
class Teszthtml {
        @Test
        void testbevitelimezo(WebDriver driver) {

            driver.get("http://127.0.0.1:5500/index.html");
            driver.findElement(By.cssSelector("#name-input")).sendKeys("Teszt Elek");
            driver.findElement(By.cssSelector("#submit-button")).click();
            String message = driver.findElement(By.cssSelector("#message-div")).getText();
            assertEquals("Hello Teszt Elek!", message);


        }
    }

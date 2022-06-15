package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SeleniumTest
public class Messagestest {
    @Test
    void testMessages(WebDriver driver)

    {
        driver.get("http://127.0.0.1:5500/messages/index.html");
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("liveAlertTimeoutBtn")).click();
        var messagetimeout = wait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(".alert"))));
        assertEquals("Nice, you triggered this alert message!", messagetimeout.getText());
        log.debug("A szöveg egyezik!");



    }
}

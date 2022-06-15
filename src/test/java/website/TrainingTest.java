package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SeleniumTest
@Slf4j
public class TrainingTest {
    @Test
    void testTraining(WebDriver driver){
        driver.get("https://www.training360.com");
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var modal = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("NewsletterModal"))));

        log.debug("Modal has appeared");


        modal.findElement(By.id("NewsletterModalCloseButton")).click();
        wait.until(ExpectedConditions.invisibilityOf(modal));
        assertFalse(modal.isDisplayed());
    }
}

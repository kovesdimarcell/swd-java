package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SeleniumTest
public class Locations {
    @Test
    void testCreatelocations (WebDriver driver){
        driver.get("http://localhost:8080/");
        String name = "LocationName"+ UUID.randomUUID();
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("create-location-link")).click();
        driver.findElement(By.id("location-name")).sendKeys(name);
        driver.findElement(By.id("location-coords")).sendKeys("00.1111,22.3333");
        driver.findElement(By.id("location-interesting-at")).sendKeys("2019-09-11T15:31:04");
        driver.findElement(By.id("location-tags")).sendKeys("Testlocation");
        driver.findElement(By.cssSelector("input.btn-primary[value='Create location']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("message-div"))));
        assertEquals("Location has been created", driver.findElement(By.id("message-div")).getText());
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("locations-table-tbody"))));
        var celltopname = driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)"));
        var celltopCoordinates = driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(3)"));
        var celltopInterestingat = driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(4)"));
        var celltopTags = driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(5)"));

        assertEquals(name, celltopname);
        assertEquals("00.1111,22.3333", celltopCoordinates);
        assertEquals("2019-09-11T15:31:04", celltopInterestingat);
        assertEquals("Testlocation", celltopTags);

    }


        LocationsPageObject page;

    @BeforeEach
    void initPage(WebDriver driver) {
        page = new LocationsPageObject(driver);
    }

    @Test
    void testCreateWithPageObject() {
        //mockaroo.com tesztadatgenerátor
        // Ne hivatkozzunk WebDriverre, mert a helye a page objectben van
        var name = "Test Location " + UUID.randomUUID();
        page
                .go()
                .clickOnCreateLocationLink()
                .fillForm(name)
                .clickOnCreateButton();


        assertEquals("Location has been created", page.waitForMessageAndGetText());
        Location created = page.waitForLocationAppears(name);

        assertEquals("1, 1", created.getCoords());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/MOCK_DATA.csv", numLinesToSkip = 1)
    void testCreateLocationDDT(String name, String lat, String lon){
        log.debug("Location: {} ({}, {})", name, lat, lon);

        page
                .go()
                .clickOnCreateLocationLink()
                .fillForm(name, lat + ","+ lon)
                .clickOnCreateButton();
    }
    @Test
    void testEdit(){
        var fixture = new LocationDatabaseFixture();
        fixture.deleteLocations();
        fixture.createLocations("teszt", 10, 10);

    }
}
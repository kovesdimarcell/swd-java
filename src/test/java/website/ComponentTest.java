package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SeleniumTest
@Slf4j
public class ComponentTest {
    @Test
    void testListTexts(WebDriver driver){
        driver.get("http://127.0.0.1:5500/components/index.html");
        var items = driver.findElements(By.tagName("li"));

        //List<String> texts = new ArrayList<>();
        //for (var item: items) {
        //    texts.add(item.getText());
       // }
        //var texts =items.stream().map(WebElement::getText).toList();
        //log.debug("List items: "+ texts);

        //assertEquals(List.of("One", "Two", "Three", "Four"), texts);

        assertThat(items)
                .extracting(WebElement::getText)
                .hasSize(4)
                .contains("One", "Three");
    }

    @Test
    void NameSearch(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var cells = driver.findElements(By.cssSelector("td:nth-child(2)"));
        assertThat(cells)
                .extracting(WebElement::getText)
                .contains("Jhon Doe", "Jane Doe", "Jack Doe");

    }

    @Test
    void testInputField(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var input = driver.findElement(By.name("text"));
        input.sendKeys("hello input");

        log.debug(input.getDomProperty("value"));


    }
    @Test
    void testCheckbox(WebDriver driver) {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var checkbox = driver.findElement(By.name("checkbox"));
        checkbox.click();
        assertTrue(checkbox.isSelected());
        assertFalse(driver.findElement(By.name("disabled-checkbox")).isEnabled());

    }

    @Test
    void testSelect(WebDriver driver){
        driver.get("http://127.0.0.1:5500/components/index.html");
        var select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByValue("option3");

        assertEquals("Option 3",select.getFirstSelectedOption().getText());
    }
}

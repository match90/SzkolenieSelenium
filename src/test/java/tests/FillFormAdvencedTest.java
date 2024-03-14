package tests;

import Base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormAdvencedTest extends BaseTest
{



    private Logger log = LoggerFactory.getLogger(FillFormBasicTest.class);

        @Test
//    @ParameterizedTest
//    @CsvFileSource(resources = "/browsers.csv")
    void fillFormBasicScenario() // przekazujemy wartość jako parametr we wszystkich metodach
    {
//        System.out.println("Start test!");

//        Krok 1 - kliknij "contact us"
        WebElement cintactUsLink = driver.findElement(By.cssSelector("#contact-link a"));
        cintactUsLink.click();
        log.info("Krok 1 - kliknięto 'Contact us'");

//        Krok 2 -
        WebElement dropDownList = driver.findElement(By.cssSelector("#id_contact"));
        Select select = new Select(dropDownList);
        select.selectByVisibleText("Webmaster");
        log.info("Krok 2 - wybrany webmaster");

//        Krok 3 - wpisz email (krok 2 skopiowany)
        WebElement emailInput =driver.findElement(By.cssSelector("#email"));
        emailInput.sendKeys("mail@mail.com");
        log.info("mail wpisany");

//        Krok 4 - wpisz nr zamówienia (krok 2 skopiowany)
        WebElement orderInput =driver.findElement(By.id("id_order"));
        orderInput.sendKeys("123456789");
        log.info("nr zamówienia wpisany");
//        Krok 5 - wpisz wiadomość
        WebElement messegeInput = driver.findElement(By.cssSelector("#message"));
        messegeInput.sendKeys("""
                tu jest tekst
                wiadomosci 
                ®""");
        log.info("Krok 5 - wiadomość wpisana");
//        Krok 6 - dodanie pliku
        WebElement fileUpload = driver.findElement(By.id("fileUpload"));
        fileUpload.sendKeys("C:\\Tranings\\TestowyPlik.txt");
        log.info("Krok 6 - dodano plik");

//        Krok 7 - kliknięcie send
        WebElement clickSend = driver.findElement(By.cssSelector("#submitMessage"));
        clickSend.click();
        log.info("Krok 7 - wysłanie wiadomosci");
//        Krok 8 - weryfikacja
        WebElement succesMessage = driver.findElement(By.className("alert-success"));
        String expectedMessage = "Your message has been successfully sent to our team.";
        String actualMessage = succesMessage.getText();
        assertThat(actualMessage).isEqualTo(expectedMessage);
        log.info("Test koniec");

    }

}



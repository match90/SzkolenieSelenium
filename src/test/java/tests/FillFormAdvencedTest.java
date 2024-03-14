package tests;

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

public class FillFormAdvencedTest {
    private final String browserName = "chrome"; // pole niezmienialne - "final"
    private final boolean headlessBrowser = false;
    private WebDriver driver;
    private final String appUrl = "http://www.automationpractice.pl/index.php";

    private Logger log = LoggerFactory.getLogger(FillFormBasicTest.class);

        @Test
//    @ParameterizedTest
//    @CsvFileSource(resources = "/browsers.csv")
    void fillFormBasicScenario() // przekazujemy wartość jako parametr we wszystkich metodach
    {
//        System.out.println("Start test!");
        log.info("Test start");
        //Inicjalizacja drivera
        driver=getDriver();
        // oczekiwanie 5s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Otwarcie URLa
        driver.get(appUrl);
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


        log.info("Test stop");
        log.debug("debug test");
//        System.out.println("Test stop!");
        driver.quit(); // zmyka proces
//        driver.close(); // zamyka okno a nie proces
        log.info("Przeglądarka zamknięta");

    }
    private WebDriver getDriver()
    {
        switch (browserName)
        {
            case "chrome" ->
            {
                WebDriverManager.chromedriver().setup(); // zarządzanie driverami
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized"); // uruchamiaj zmaksymalizowane
                chromeOptions.addArguments("--remote-allow-origins=*"); // daje możliwość kontroli nad przeglądarką
                if (this.headlessBrowser)
                {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
            }

            case "firefox" ->
            {
                WebDriverManager.firefoxdriver().setup(); // zarządzanie driverami
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized"); // uruchamiaj zmaksymalizowane
                firefoxOptions.addArguments("--remote-allow-origins=*"); // daje możliwość kontroli nad przeglądarką
                if (this.headlessBrowser)
                {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
            }

            case "edge" ->
            {
                WebDriverManager.edgedriver().setup(); // zarządzanie driverami
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized"); // uruchamiaj zmaksymalizowane
                edgeOptions.addArguments("--remote-allow-origins=*"); // daje możliwość kontroli nad przeglądarką
                if (this.headlessBrowser)
                {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
            }
            default ->
            {
                System.out.println("Nieprawidłowa przeglądarka");
            }
        }
        return driver;
    }

}

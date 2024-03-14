package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.FillFormBasicTest;

import java.time.Duration;

public class BaseTest {
    protected final String appUrl = "http://www.automationpractice.pl/index.php";
    protected final String browserName = "chrome"; // pole niezmienialne - "final"
    protected final boolean headlessBrowser = false;
    protected WebDriver driver;
    private Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    void setApp()
    {
        log.info("Test start");
        //Inicjalizacja drivera
        driver = getDriver();
        // oczekiwanie 5s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Otwarcie URLa
        driver.get(appUrl);
    }
    @AfterEach
    void tearDown()
    {
        log.info("Test stop");
        log.debug("debug test");
//        System.out.println("Test stop!");
        driver.quit(); // zmyka proces
//        driver.close(); // zamyka okno a nie proces
        log.info("Przeglądarka zamknięta");
    }

    protected WebDriver getDriver() {
        switch (browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup(); // zarządzanie driverami
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized"); // uruchamiaj zmaksymalizowane
                chromeOptions.addArguments("--remote-allow-origins=*"); // daje możliwość kontroli nad przeglądarką
                if (this.headlessBrowser) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
            }

            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup(); // zarządzanie driverami
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized"); // uruchamiaj zmaksymalizowane
                firefoxOptions.addArguments("--remote-allow-origins=*"); // daje możliwość kontroli nad przeglądarką
                if (this.headlessBrowser) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
            }

            case "edge" -> {
                WebDriverManager.edgedriver().setup(); // zarządzanie driverami
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized"); // uruchamiaj zmaksymalizowane
                edgeOptions.addArguments("--remote-allow-origins=*"); // daje możliwość kontroli nad przeglądarką
                if (this.headlessBrowser) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
            }
            default -> {
                System.out.println("Nieprawidłowa przeglądarka");
            }
        }
        return driver;
    }
}

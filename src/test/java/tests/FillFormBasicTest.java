package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FillFormBasicTest
{
    private final String browsername = "chrome";
    private final boolean headlessBrowser = false;
    private WebDriver driver;
    private final String appUrl = "http://www.automationpractice.pl/index.php";

    @Test
    void fillFormBasicScenario()
    {
        System.out.println("Start test!");

        //Inicjalizacja drivera
        driver=getDriver();
        //Otwarcie URLa
        driver.get(appUrl);

        System.out.println("Test stop!");

    }
    private WebDriver getDriver()
    {
        switch (this.browsername)
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

package de.goldmann.tienda;

import static org.junit.Assert.assertNotNull;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.lang3.SystemUtils;
import org.fluentlenium.adapter.FluentAdapter;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;


public abstract class WebTest extends FluentAdapter {

    protected static final String HOST_ADRESS = "http://localhost:4200";

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = setupDriver();
        assertNotNull(driver);
        initFluent(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private String getChromeDriverPath() {
        final Path currentRelativePath = Paths.get(".");
        final String driverFile = currentRelativePath + FileSystems.getDefault().getSeparator() + "chromedriver";
        return SystemUtils.IS_OS_WINDOWS == false ? driverFile : driverFile + ".exe";
    }

    protected WebDriver setupDriver() {
        final DesiredCapabilities dc = new DesiredCapabilities();
        final LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.FINE);
        dc.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        final ChromeDriver chromeDriver = new ChromeDriver(dc);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
        return chromeDriver;
    }

    protected void logout(final FluentWait<WebDriver> wait) {
        final WebElement logoutBtn = wait.until(new VisibilityFunction(By.id("logoutBtn")));
        logoutBtn.click();
    }

    protected void waitUntilElementIsDisplayed(final String elementId) {
        await().until($(elementId)).displayed();
    }

}

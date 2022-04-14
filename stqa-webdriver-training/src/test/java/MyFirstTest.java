import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void myTest() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
    }

    @Test
    public void loginTest() {
        navigateTo("http://localhost/litecart/admin/");
        login("admin", "admin");
    }

    @Test
    public void Task7() {
        navigateTo("http://localhost/litecart/");
        List<WebElement> productStickerQuantity = driver.findElements(By.xpath("//li[count(.//div[contains(@class,'sticker')])=1]"));
        List<WebElement> productQuantity = driver.findElements(By.xpath("//li[contains(@class,'product')]"));
        assertEquals(productQuantity.size(), productStickerQuantity.size());
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private void navigateTo(String webPage) {
        driver.get(webPage);
    }

    private void login(String username, String password) {
        putText(By.name("username"), username);
        putText(By.name("password"), password);
        click(By.name("login"));
    }

    private void click(By locator) {
        if (isElementPresent(driver, locator))
            driver.findElement(locator).click();
    }

    private void putText(By locator, String text) {
        if (isElementPresent(driver, locator))
            driver.findElement(locator).sendKeys(text);
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex) {
            throw ex;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
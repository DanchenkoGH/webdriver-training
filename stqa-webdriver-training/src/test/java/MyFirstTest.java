import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
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
        driver.findElement(locator).click();
    }

    private void putText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }
}
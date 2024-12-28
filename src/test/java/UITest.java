import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UITest {
    ChromeDriver driver;
    String url = "http://10.10.215.5";

    private void setLogin(String email) {
        WebElement login = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#loginEmail")));
        login.sendKeys(email);
    }

    private void setPassword(String password) {
        driver.findElement(By.cssSelector("#loginPassword")).sendKeys(password);
    }

    private void clickEnter() {
        driver.findElement(By.cssSelector("#authButton")).click();
    }

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @AfterEach
    public void teardown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void positiveTestLogin() {
        setLogin("test@protei.ru");
        setPassword("test");
        clickEnter();

        WebElement mainTitle = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
        assert mainTitle.isDisplayed();
        assert mainTitle.getAttribute("class").equals("uk-card-title");
        assert mainTitle.getText().equals("Добро пожаловать!");
    }

    @Test
    public void testIncorrectEmail() {
        setLogin("testnotexist@protei.ru");
        setPassword("test");
        clickEnter();

        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".uk-alert p")));

        assert errorMessage.isDisplayed();
        assert errorMessage.getText().equals("Неверный E-Mail или пароль");
    }

    @Test
    public void testIncorrectPassword() {
        setLogin("test@protei.ru");
        setPassword("test123");
        clickEnter();

        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".uk-alert p")));

        assert errorMessage.isDisplayed();
        assert errorMessage.getText().equals("Неверный E-Mail или пароль");
    }
}

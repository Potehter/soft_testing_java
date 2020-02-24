package addressbook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateContact {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        login("admin", "secret");
    }

    @Test
    public void testCreateContact() throws Exception {
        initContactPage();
        fillContactInfo(new ContactData("Name", "Middle name", "Surname", "nick", "title", "company"));
        submitContactCreation();
        gotoHomePage();
    }

    private void gotoHomePage() {
        driver.findElement(By.linkText("home page")).click();
    }

    private void submitContactCreation() {
        driver.findElement(By.name("submit")).click();
    }

    private void fillContactInfo(ContactData contactData) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(contactData.getName());
        driver.findElement(By.name("middlename")).clear();
        driver.findElement(By.name("middlename")).sendKeys(contactData.getMidName());
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(contactData.getSurname());
        driver.findElement(By.name("nickname")).clear();
        driver.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        driver.findElement(By.name("title")).clear();
        driver.findElement(By.name("title")).sendKeys(contactData.getTitle());
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys(contactData.getCompany());
    }

    private void initContactPage() {
        driver.findElement(By.linkText("add new")).click();
    }

    private void login(String username, String password) {
        driver.get("http://localhost:8080/addressbook/");
        driver.findElement(By.name("user")).clear();
        driver.findElement(By.name("user")).sendKeys(username);
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}

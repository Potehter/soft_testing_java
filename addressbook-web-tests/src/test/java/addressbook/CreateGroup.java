package addressbook;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class CreateGroup {
  private WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  @Test
  public void testCreateGroup() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupInfo(new GroupData("test01", "test2", "test3"));
    submitGroupCreation();
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

  private void submitGroupCreation() {
    driver.findElement(By.name("submit")).click();
  }

  private void fillGroupInfo(GroupData groupData) {
    driver.findElement(By.name("group_name")).clear();
    driver.findElement(By.name("group_name")).sendKeys(groupData.getName());
    driver.findElement(By.name("group_header")).clear();
    driver.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    driver.findElement(By.name("group_footer")).clear();
    driver.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  private void initGroupCreation() {
    driver.findElement(By.name("new")).click();
  }

  private void gotoGroupPage() {
    driver.findElement(By.linkText("groups")).click();
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

package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }

    public void submitAlert() {
        driver.switchTo().alert().accept();
    }
}

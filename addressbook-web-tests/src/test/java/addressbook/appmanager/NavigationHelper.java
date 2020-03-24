package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                    && driver.findElement(By.tagName("h1")).getText().equals("Groups")
                    && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void submitAlert() {
        driver.switchTo().alert().accept();
    }
}

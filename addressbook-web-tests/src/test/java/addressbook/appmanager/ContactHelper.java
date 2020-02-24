package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactInfo(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMidName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText("test01");
        }
    }

    public void initContactPage() {
        click(By.linkText("add new"));
    }

    public void editContact() {
        click(By.xpath("//img[@title='Edit']"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void submitContactEdition() {
        click(By.name("update"));
    }

    public void initDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public boolean ThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contactData, boolean creation) {
        initContactPage();
        fillContactInfo(new ContactData("Name", "Middle name", "Surname", "nick", "title", "company"), true);
        submitContactCreation();
    }
}

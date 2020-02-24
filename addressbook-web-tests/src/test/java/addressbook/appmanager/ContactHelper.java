package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactInfo(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMidName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
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
}

package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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

    public void editContact(int i) {
        //driver.findElements(By.name("entry")).get(i).findElement(By.xpath("//img[@title='Edit']")).click();
        driver.findElements(By.xpath("//img[@title='Edit']")).get(i).click();
    }

    public void selectContact(int i) {
        driver.findElements(By.name("selected[]")).get(i).click();
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

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstName = element.findElements(By.cssSelector("td")).get(2).getText();
            String lastName = element.findElements(By.cssSelector("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstName, lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}

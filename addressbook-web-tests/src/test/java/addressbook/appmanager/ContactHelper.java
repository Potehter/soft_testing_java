package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public void create(ContactData contact) {
        initContactPage();
        fillContactInfo(contact);
        submitContactCreation();
    }

    public void modify(int id, ContactData contact) {
       editContactById(id);
       fillContactInfo(contact);
       submitContactEdition();
    }

    public void delete(ContactData deletedContact) {
        selectContact(deletedContact.getId());
        initDeleteContact();
    }

    public void initContactPage() {
        click(By.linkText("add new"));
    }

    public void editContact(int i) {
         driver.findElements(By.xpath("//img[@title='Edit']")).get(i).click();
    }
    public void editContactById(int id) {
        driver.findElement(By.cssSelector("a[href='edit.php?id="+ id + "'")).click();
    }

    public void submitContactEdition() {
        click(By.name("update"));
    }

    public void selectContact(int id) {
        driver.findElement(By.id(String.valueOf(id))).click();
    }

    public void initDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public boolean ThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contactData, boolean creation) {
        initContactPage();
        fillContactInfo(new ContactData().
                withName("Name").withMidName("Middle name").withSurname("Surname").
                withNickname("nick").withTitle("title").withCompany("company"));
        submitContactCreation();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstName = element.findElements(By.cssSelector("td")).get(2).getText();
            String lastName = element.findElements(By.cssSelector("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withName(firstName).withSurname(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstName = element.findElements(By.cssSelector("td")).get(2).getText();
            String lastName = element.findElements(By.cssSelector("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withName(firstName).withSurname(lastName);
            contacts.add(contact);
        }
        return contacts;
    }


}

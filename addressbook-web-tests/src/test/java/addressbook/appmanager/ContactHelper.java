package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
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

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectContact(contact.getId());
        addToGroup(group.getName());
    }

    public void deleteContactFromGroup(GroupData group, ContactData contact) {
        contactWithGroup(group.getName());
        deleteFromGroup(contact.getId());
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
            String allPhones = element.findElements(By.cssSelector("td")).get(5).getText();;
            String allEmails = element.findElements(By.cssSelector("td")).get(4).getText();;
            String allAddresses = element.findElements(By.cssSelector("td")).get(3).getText();;
            ContactData contact = new ContactData().withId(id).withName(firstName).withSurname(lastName).
                    withAllPhones(allPhones).withAllAddress(allAddresses).withAllEmails(allEmails);
            contacts.add(contact);
        }
        return contacts;
    }


    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEdit() {
        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = driver.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = driver.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = driver.findElement(By.name("work")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getText();
        String address2 = driver.findElement(By.name("address2")).getText();
        ContactData contact = new ContactData().withName(firstName).withSurname(lastName).
                withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).
                withEmail(email).withEmail2(email2).withEmail3(email3).
                withAddress(address).withAddressSecond(address2);
        return contact;
    }

    public void addToGroup(String group) {
        WebElement selectElem = driver.findElement(By.name("to_group"));
        Select select = new Select(selectElem);
        select.selectByVisibleText(group);
        driver.findElement(By.name("add")).click();
    }

    public void contactWithGroup(String group) {
        WebElement selectElem = driver.findElement(By.name("group"));
        Select select = new Select(selectElem);
        select.selectByVisibleText(group);
    }

    public void deleteFromGroup(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
        driver.findElement(By.name("remove")).click();
    }
}
package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreate extends BaseTest {

    @Test
    public void testCreateContact() throws Exception {
        app.getContactHelper().initContactPage();
        app.getContactHelper().fillContactInfo(new ContactData("Name", "Middle name", "Surname", "nick", "title", "company"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}
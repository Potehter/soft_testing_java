package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class CreateContact extends BaseTest {

    @Test
    public void testCreateContact() throws Exception {
        app.getContactHelper().initContactPage();
        app.getContactHelper().fillContactInfo(new ContactData("Name", "Middle name", "Surname", "nick", "title", "company"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}

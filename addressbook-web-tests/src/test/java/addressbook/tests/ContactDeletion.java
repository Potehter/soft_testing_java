package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletion extends BaseTest {

    @Test
    public void testDeleteContact() throws Exception {
        if (! app.getContactHelper().ThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Name", "Middle name", "Surname", "nick", "title", "company"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initDeleteContact();
        app.getNavigationHelper().submitAlert();
    }

}

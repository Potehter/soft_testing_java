package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactEdit extends BaseTest {

    @Test
    public void testEditContact() throws Exception {
        if (! app.getContactHelper().ThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Name", "Middle name", "Surname", "nick", "title", "company"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactInfo(new ContactData("Name_edited", "Middle name", "Surname_edited", "nick_edited", "title", "company"), false);
        app.getContactHelper().submitContactEdition();
        app.getNavigationHelper().gotoHomePage();
    }

}

package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactEdit extends BaseTest {

    @Test
    public void testEditContact() throws Exception {
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactInfo(new ContactData("Name_edited", "Middle name", "Surname_edited", "nick_edited", "title", "company"));
        app.getContactHelper().submitContactEdition();
        app.getNavigationHelper().gotoHomePage();
    }

}

package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletion extends BaseTest {

    @Test
    public void testDeleteContact() throws Exception {
        app.getContactHelper().selectContact();
        app.getContactHelper().initDeleteContact();
        app.getNavigationHelper().submitAlert();
    }

}

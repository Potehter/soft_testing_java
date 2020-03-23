package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactDeletion extends BaseTest {

    @Test
    public void testDeleteContact() throws Exception {
        if (! app.getContactHelper().ThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Name", "Middle name", "Surname", "nick", "title", "company"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initDeleteContact();
        app.getNavigationHelper().submitAlert();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size() - 1);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}

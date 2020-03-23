package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactEdit extends BaseTest {

    @Test
    public void testEditContact() throws Exception {
        if (! app.getContactHelper().ThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Name", "Middle name", "Surname", "nick", "title", "company"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().editContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Name_edited", "Middle name", "Surname_edited", "nick_edited", "title", "company");
        app.getContactHelper().fillContactInfo(contact, false);
        app.getContactHelper().submitContactEdition();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}

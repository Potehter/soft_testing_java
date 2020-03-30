package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactEdit extends BaseTest {

    @BeforeMethod
    public void ensurePreconditiions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().
                    withName("Name").withMidName("Middle name").withSurname("Surname").withNickname("nick").
                    withTitle("title").withCompany("company"), true);
        }
        app.goTo().homePage();
    }

    @Test
    public void testEditContact() throws Exception {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).withName("Name_edited").withSurname("Surname_edited").withMidName("mid name edir").
                withNickname("nick").withTitle("title").withCompany("company");
        app.contact().modify(modifiedContact.getId(), contact);
        app.goTo().homePage();
        assertThat(app.db().contacts().size(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }



}

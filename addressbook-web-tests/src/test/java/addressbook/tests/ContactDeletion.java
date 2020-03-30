package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletion extends BaseTest {

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
    public void testDeleteContact() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().submitAlert();
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after =  app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }



}

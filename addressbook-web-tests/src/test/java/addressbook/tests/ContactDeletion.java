package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletion extends BaseTest {

    @BeforeMethod
    public void ensurePreconditiions() {
        if (! app.contact().ThereAContact()) {
            app.contact().create(new ContactData().
                    withName("Name").withMidName("Middle name").withSurname("Surname").withNickname("nick").
                    withTitle("title").withCompany("company"), true);
        }
        app.goTo().homePage();
    }

    @Test
    public void testDeleteContact() throws Exception {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().submitAlert();
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }



}

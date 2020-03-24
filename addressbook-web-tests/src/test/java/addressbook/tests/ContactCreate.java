package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreate extends BaseTest {

    @Test
    public void testCreateContact() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().
                withName("Name").withMidName("Middle name").withSurname("Surname").
                withNickname("nick").withTitle("title").withCompany("company");
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        contact.withId(after.stream().mapToInt( (g) -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(contact)));
    }

}

package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(
                    new GroupData().withName("test01").withFooter("test2").withHeader("test3"));
        }
        app.goTo().groupPage();

        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().
                    withName("Name").withMidName("Middle name").withSurname("Surname").withNickname("nick").
                    withTitle("title").withCompany("company"), true);
        }
        app.goTo().homePage();
    }

    @Test
    public void testAddToGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        GroupData group =  app.db().groups().iterator().next();
        app.contact().addContactToGroup(contact, group);
        ContactData addedContact = app.db().contacts().iterator().next();
        assertThat(addedContact.getGroups(), equalTo(contact.getGroups().withAdded(group)));
    }

}

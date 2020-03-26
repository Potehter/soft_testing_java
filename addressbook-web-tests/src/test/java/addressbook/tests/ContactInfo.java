package addressbook.tests;

import addressbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactInfo extends BaseTest {

    @Test
    public void testContactInfo() {
        ContactData contact = app.contact().all().iterator().next();
        app.contact().editContactById(contact.getId());
        ContactData contactInfoToCompare = app.contact().infoFromEdit();
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoToCompare)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoToCompare)));
        assertThat(contact.getAllAddress(), equalTo(contactInfoToCompare.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s -> !s.equals("")))
                .map(ContactInfo::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]]", "");
    }
}

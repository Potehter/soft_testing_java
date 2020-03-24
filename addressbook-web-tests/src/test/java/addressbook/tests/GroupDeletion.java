package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletion extends BaseTest {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().ThereAGroup()) {
      app.group().createGroup(new GroupData()
              .withName("test01").withHeader("test2").withFooter("test3"));
    }
    app.goTo().groupPage();
  }

  @Test
  public void testDeleteGroup() throws Exception {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }



}

package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreate extends BaseTest {

  @Test
  public void testCreateGroup() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().
            withName("test01").withHeader("test2").withFooter("test3");
    app.group().create(group);
    Groups after = app.group().all();
    group.withId(after.stream().mapToInt( (g) -> g.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(group)));
  }




}

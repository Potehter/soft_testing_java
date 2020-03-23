package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupEdit extends BaseTest {

  @Test
  public void testGroupEdit() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().ThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test01", "test2", "test3"));
    }
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initEditGroup();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test01_edited", "test2_edited", "test3_edited");
    app.getGroupHelper().fillGroupInfo(group);
    app.getGroupHelper().submitGroupEdit();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}

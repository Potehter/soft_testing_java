package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletion extends BaseTest {

  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().ThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test01", "test2", "test3"));
    }
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
  }


}

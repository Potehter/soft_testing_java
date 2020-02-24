package addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletion extends BaseTest {

  @Test
  public void testCreateGroup() throws Exception {
    app.getGroupHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
  }


}

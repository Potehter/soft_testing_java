package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupEdit extends BaseTest {

  @Test
  public void testGroupEdit() throws Exception {
    app.getGroupHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initEditGroup();
    app.getGroupHelper().fillGroupInfo(new GroupData("test01_edited", "test2_edited", "test3_edited"));
    app.getGroupHelper().submitGroupEdit();
  }


}

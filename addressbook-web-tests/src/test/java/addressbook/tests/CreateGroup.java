package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class CreateGroup extends BaseTest {

  @Test
  public void testCreateGroup() throws Exception {
    app.getGroupHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupInfo(new GroupData("test01", "test2", "test3"));
    app.getGroupHelper().submitGroupCreation();
  }


}

package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends BaseHelper{

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void submitGroupCreation() {
      click(By.name("submit"));
    }

    public void fillGroupInfo(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
      click(By.name("new"));
    }

    public void gotoGroupPage() {
      click(By.linkText("groups"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initEditGroup() {
        click(By.name("edit"));
    }

    public void submitGroupEdit() {
        click(By.name("update"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }
}

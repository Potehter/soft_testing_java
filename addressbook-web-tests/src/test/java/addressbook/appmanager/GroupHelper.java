package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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


    public void selectGroup(int i) {
        driver.findElements(By.name("selected[]")).get(i).click();
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

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupInfo(groupData);
        submitGroupCreation();
    }

    public boolean ThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String groupName = element.getText();
            GroupData group = new GroupData(groupName, null, null);
            groups.add(group);
        }
        return groups;
    }

    public void returnToGroupPage() { click(By.cssSelector("a[href='group.php']"));
    }
}

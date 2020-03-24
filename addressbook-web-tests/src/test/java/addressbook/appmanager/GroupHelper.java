package addressbook.appmanager;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupInfo(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initEditGroup();
        fillGroupInfo(group);
        submitGroupEdit();
        returnToGroupPage();
    }

    public void delete(GroupData deletedGroup) {
        selectGroupById(deletedGroup.getId());
        deleteGroup();
        returnToGroupPage();
    }


    public void initGroupCreation() {
      click(By.name("new"));
    }


    public void selectGroupById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public List<GroupData> list() {
            List<GroupData> groups = new ArrayList<GroupData>();
            List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
            for (WebElement element : elements) {
                String groupName = element.getText();
                GroupData group = new GroupData().withName(groupName);
                groups.add(group);
            }
            return groups;
    }

    public Groups all() {
        Groups groups = new Groups();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String groupName = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(groupName));
        }
        return groups;
    }

    public void returnToGroupPage() { click(By.cssSelector("a[href='group.php']"));
    }
}

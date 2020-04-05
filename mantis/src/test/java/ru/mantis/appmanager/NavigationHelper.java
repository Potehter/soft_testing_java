package ru.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelper {


    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String user, String pass) {
        driver.get(app.getProperty("web.baseURL") + "/login_page.php");
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), pass);
        click(By.cssSelector("input[type='submit']"));
    }

    public void resetPassword(int id) {
        driver.get(app.getProperty("web.baseURL") + "/manage_user_edit_page.php?user_id=" + id);
        //click(By.cssSelector("form[id='manage-user-reset-form'] > input[type='submit']"));
        click(By.xpath("//form[@id='manage-user-reset-form']//input[@type='submit']"));
    }
}

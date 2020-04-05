package ru.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class RegistrationHelper extends BaseHelper{




    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String user, String email) {
        driver.get(app.getProperty("web.baseURL") + "/signup_page.php");
        type(By.name("username"), user);
        type(By.name("email"), email);
        //click(By.cssSelector("input[value='Зарегистрироваться']"));
        click(By.cssSelector("input[type='submit']"));
    }

    public void finish(String confirmationLink, String password) {
        driver.get(confirmationLink);
        type(By.id("realname"), "Автотест");
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}

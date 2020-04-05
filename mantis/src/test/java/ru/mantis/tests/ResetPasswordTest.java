package ru.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.mantis.model.MailMessage;
import ru.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends BaseTest {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testReset() throws IOException {
        List<UserData> users = app.db().users();
        UserData testUser = users.get(users.size() - 1);
        String password = app.getProperty("default.password");
        loginAdminAndResetPassword(testUser);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, testUser.getEmail());
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(testUser.getUsername(), password));
    }

    private void loginAdminAndResetPassword(UserData testUser) {
        app.navigation().login(app.getProperty("web.login"), app.getProperty("web.password"));
        app.navigation().resetPassword(testUser.getId());
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter( (m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}

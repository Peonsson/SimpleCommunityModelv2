import org.eclipse.persistence.sessions.Login;
import org.junit.Test;
import org.peonsson.com.BusinessLogic.UserHandler;
import org.peonsson.com.ViewModels.LoginViewModel;
import org.peonsson.com.ViewModels.ReturnCodeViewModel;
import org.peonsson.com.ViewModels.UserViewModel;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by robin on 19/11/15.
 */
public class UserTest {

    @Test
    public void testBrowse() throws Exception {
        List<UserViewModel> users = UserHandler.getUsers();

        assertTrue(users.size() > 0);
    }

    @Test
    public void testGetUser() throws Exception {
        UserViewModel user = UserHandler.getUser(1);

        assertTrue(user != null);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        String uname = "peonsson";
        String pass = "password";

        LoginViewModel usr = new LoginViewModel(uname, pass);

        ReturnCodeViewModel returnVal = UserHandler.loginUser(usr);

        assertTrue(returnVal.getId() != -1);
    }

    @Test
    public void testLoginWrongPassword() throws Exception {
        String uname = "test";
        String pass = "asdaoidsjaosdi";

        LoginViewModel usr = new LoginViewModel(uname, pass);

        ReturnCodeViewModel returnVal = UserHandler.loginUser(usr);

        assertTrue(returnVal.getId() == -1);
    }

    @Test
    public void testLoginNonexistantUser() throws Exception {
        String uname = "doesntexist";
        String pass = "asdaoidsjaosdi";

        LoginViewModel usr = new LoginViewModel(uname, pass);

        ReturnCodeViewModel returnVal = UserHandler.loginUser(usr);

        assertTrue(returnVal.getId() == -1);
    }
}
import authorization.Roles;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import play.mvc.Result;

import service.UserService;
import controllers.UserController;
import models.dao.User;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getUser() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User(1,"Chesnok","1", new Timestamp(156780),false, Roles.ADMIN);
        users.add(user);

        when(userService.getUsers()).thenReturn(users);
        Result result = userController.getUsers();

        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
    }

    @Test
    public void test_result_getUsers() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User(1,"Chesnok","1", new Timestamp(156780),false, Roles.ADMIN);
        users.add(user);

        when(userService.getUsers()).thenReturn(users);
        Result result = userController.getUsers();

        assertEquals(contentAsString(result), "[{\"id\":1,\"login\":\"Chesnok\",\"password_hash\":1,\"created_at\":156780,\"is_deleted\":false,\"role\":\"ADMIN\"}]");
    }

    @Test
    public void test_deleteUsers() {
        User user = new User(1,"Chesnok","1", new Timestamp(156780),false, Roles.ADMIN);
        doNothing().when(userService).deleteUser(user.getId());
        Result result = userController.deleteUser(user.getId());

        assertEquals(OK, result.status());
    }

    @Test
    public void test_getUserById() {
        User user = new User(1,"Chesnok","1", new Timestamp(156780),false, Roles.ADMIN);
        when(userService.getUserById(user.getId())).thenReturn(user);
        Result result = userController.getUserById(user.getId());

        assertEquals(OK, result.status());
        assertEquals(contentAsString(result), "{\"id\":1,\"login\":\"Chesnok\",\"password_hash\":1,\"created_at\":156780,\"is_deleted\":false,\"role\":\"ADMIN\"}");
    }
}
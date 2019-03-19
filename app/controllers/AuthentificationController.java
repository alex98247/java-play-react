package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

public class LoginForm {

    private String login;
    private String password;

    public List<validationerror> validate() {
        UserService userService = (UserService) ServicesInstances.USER_SERVICE.getService();
        User user = userService.getByLoginAndPassword(getLogin(), getPassword());
        if (user == null) {
            List<validationerror> errors = new ArrayList<validationerror>();
            errors.add(new ValidationError("login", "Login or password is incorrect"));
            return errors;
        }
        return null;
    }

    public String getLogin() {
        return this.login;
    }
    public String getPassword() {
        return this.password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm {login: "+this.login+"}";
    }

}

public class AuthentificationController extends Controller {

    private static Form<loginform> loginForm = Form.form(LoginForm.class);

    @Transactional
    public static Result login() {
        return ok(login.render(loginForm, flash()));
    }

    @Transactional
    public static Result loginSubmit() {
        Form<loginform> submittedForm = loginForm.bindFromRequest("login", "password");
        if (!submittedForm.hasErrors()) {
            LoginForm formObj = submittedForm.get();
            Logger.debug("User exists, no errors. Found user is: "+formObj);
            session().put("u", formObj.getLogin());
            return redirect(routes.AuthentificationController.dashboard());
        }
        return badRequest(login.render(submittedForm, flash()));
    }
}

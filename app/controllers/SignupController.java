package controllers;

import is.ru.honn.news.domain.UserRegistration;
import is.ru.honn.news.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.data.Form;
import play.mvc.*;

import views.html.signup.signup;

import static play.data.Form.form;

/**
 * Created by Snaebjorn on 10/22/2015.
 */
public class SignupController extends Controller {
    final static Form<UserRegistration> signupForm = form(UserRegistration.class);
    protected ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/app.xml");

    public Result blank() {
        return ok(signup.render(signupForm));
    }

    public Result submit() {
        Form<UserRegistration> filledForm = signupForm.bindFromRequest();

        if (!"true".equals(filledForm.field("accept").value()))
            filledForm.reject("accept", "You must accept the terms and conditions");
        if (filledForm.field("username").value().length() < 4)
            filledForm.reject("username", "Username must be at least 4 characters");
        if (!filledForm.field("password").value().equals(filledForm.field("repeatPassword").value()))
            filledForm.reject("repeatPassword", "Passwords do not match");
        if (filledForm.field("password").value().length() < 6)
            filledForm.reject("password", "Password too short");

        if (filledForm.hasErrors())
            return badRequest(signup.render(filledForm));

        UserRegistration created = filledForm.get();
        UserService service = (UserService) ctx.getBean("userService");
        int newId = service.signup(created);
        System.out.println(newId);
        session("username", created.getUsername());
        session("userid", Integer.toString(newId));

        return ok(views.html.signup.summary.render(created));
    }

}

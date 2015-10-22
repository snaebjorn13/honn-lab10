package controllers;

import is.ru.honn.news.domain.User;
import is.ru.honn.news.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import static play.data.Form.form;

/**
 * Created by Snaebjorn on 10/22/2015.
 */
public class UserController extends Controller {
    protected ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/app.xml");

    public Result blankProfile() {
        String username = session().get("username");
        UserService userService = (UserService) ctx.getBean("userService");

        int id = (Integer.valueOf(session().get("userid"))).intValue();
        System.out.println("Session id: " + id);
        User user = userService.getUser(id);

        Form<User> profileForm = form(User.class);
        profileForm = profileForm.fill(user);

        return ok(profile.render(profileForm));
    }

    public Result submit() {
        Form<User> profileForm = form(User.class);
        Form<User> updatedForm = profileForm.bindFromRequest();

        if (updatedForm.field("username").value().length() < 4)
            updatedForm.reject("username", "Username must be at least 4 characters");
        if (updatedForm.hasErrors())
            return badRequest(profile.render(updatedForm));

        User user = updatedForm.get();
        UserService service = (UserService) ctx.getBean("userService");
        service.updateUser(user);

        return ok(profile.render(updatedForm));
    }

}

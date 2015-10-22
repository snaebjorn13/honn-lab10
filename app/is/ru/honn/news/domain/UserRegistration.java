package is.ru.honn.news.domain;

/**
 * Created by Snaebjorn on 10/22/2015.
 */
public class UserRegistration extends User {
    private String repeatPassword;

    public UserRegistration() {
    }

    public UserRegistration(int id, String name, String username, String password, String email, String repeatPassword) {
        super(id, name, username, password, email);
        this.repeatPassword = repeatPassword;
    }

    public UserRegistration(String name, String username, String password, String email, String repeatPassword) {
        super(name, username, password, email);
        this.repeatPassword = repeatPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

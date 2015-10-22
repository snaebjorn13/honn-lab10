package is.ru.honn.news.service;


import is.ru.honn.news.data.UserDataGateway;
import is.ru.honn.news.domain.User;

import java.util.logging.Logger;

/**
 * Created by Snaebjorn on 10/22/2015.
 */
public class UserServiceData implements UserService {
    Logger logger = Logger.getLogger(this.getClass().getName());
    protected UserDataGateway userDataGateway;

    @Override
    public int signup(User user) {
        return this.userDataGateway.addUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = this.userDataGateway.getUserByUsername(username);
        if (user == null)
            return null;
        if (!user.getPassword().equals(password))
            return null;
        return user;
    }

    @Override
    public void setUserDataGateway(UserDataGateway userDataGateway) {
        this.userDataGateway = userDataGateway;
    }

    @Override
    public User getUser(int id) {
        return userDataGateway.getUserById(id);
    }

    @Override
    public User getUser(String username) {
        return userDataGateway.getUserByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userDataGateway.updateUser(user);
    }
}

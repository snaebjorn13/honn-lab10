package is.ru.honn.news.service;

import is.ru.honn.news.data.UserDataGateway;
import is.ru.honn.news.domain.User;

/**
 * Created by Snaebjorn on 10/22/2015.
 */
public interface UserService {
    int signup(User user);
    User login(String username, String password);
    void setUserDataGateway(UserDataGateway userDataGateway);
    User getUser(int id);
    User getUser(String username);
    void updateUser(User user);
}

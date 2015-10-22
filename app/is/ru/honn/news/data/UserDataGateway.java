package is.ru.honn.news.data;

import is.ru.honn.news.domain.User;
import is.ruframework.data.RuDataAccess;

/**
 * Created by Snaebjorn on 10/22/2015.
 */
public interface UserDataGateway extends RuDataAccess {
    User getUserById(int id);
    User getUserByUsername(String username);
    int addUser(User user);
    void updateUser(User user);
}

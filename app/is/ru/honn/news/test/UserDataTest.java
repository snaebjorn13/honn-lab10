package is.ru.honn.news.test;

import is.ru.honn.news.data.UserDataGateway;
import is.ru.honn.news.domain.User;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;

/**
 * Created by Snaebjorn on 10/22/2015.
 */
public class UserDataTest {
    public static void main(String[] args) throws RuException {
        new UserDataTest();
    }

    public UserDataTest() throws RuException {
        RuDataAccessFactory factory = RuDataAccessFactory.getInstance("app.xml");
        UserDataGateway userDataGateway = (UserDataGateway) factory.getDataAccess("userDataGateway");

        // userDataGateway.addUser(new User("Dilbert", "dilbert", "abc123", "dilbert@ru.is"));
        User user = userDataGateway.getUserByUsername("dilbert");
        User user2 = userDataGateway.getUserById(1);
        System.out.println(user);
        System.out.println(user2);
    }
}

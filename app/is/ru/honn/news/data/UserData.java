package is.ru.honn.news.data;

import is.ru.honn.news.data.UserDataGateway;
import is.ru.honn.news.domain.User;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Snaebjorn on 10/22/2015.
 */
public class UserData extends RuData implements UserDataGateway {
    @Override
    public User getUserById(int id) {
        JdbcTemplate queryUser = new JdbcTemplate(getDataSource());
        return (User) queryUser.queryForObject(
                "select * from usersdt where id = ?",
                new UserRowMapper(), id
        );
    }

    @Override
    public User getUserByUsername(String username) {
        JdbcTemplate queryUser = new JdbcTemplate(getDataSource());
        return (User) queryUser.queryForObject(
                "select * from usersdt where username = ?",
                new UserRowMapper(), username
        );
    }

    @Override
    public int addUser(User user) {
        SimpleJdbcInsert insertUser =
                new SimpleJdbcInsert(getDataSource())
                    .withTableName("usersdt")
                    .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("name", user.getName());
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());
        parameters.put("email", user.getEmail());

        int returnKey = 0;
        try {
            returnKey = insertUser.executeAndReturnKey(parameters).intValue();
        } catch (DataIntegrityViolationException divex) {
            log.warning("Duplicate entry");
        }

        return returnKey;
    }

    @Override
    public void updateUser(User user) {
        JdbcTemplate template = new JdbcTemplate(getDataSource());

        template.update("update usersdt set name = ?, username = ?, email = ? where id = ?",
                user.getName(), user.getUsername(), user.getEmail(), user.getId());
    }
}

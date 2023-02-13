package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> getAllUsers();
    User getUserById(@Param("id")int id);
    List<User> getUserByName(String name);

    int addUser(User user);
    int updateUser(User user);

    int deleteUsers(String id);


    class varchar {
    }
}

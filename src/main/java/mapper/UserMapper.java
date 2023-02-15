package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> getAllUsers();
    User getUserById(@Param("id")int id);
    List<User> getUserByName(String name);

    int addUser(User user);
    int updateUser(User user);
    int deleteUsers(String id);

    //参数传递
    List<User> getUserByParams1(@Param("map") Map map);
    //按照顺序传参
    List<User> getUserByParams2(String aaa,String bbb);

    //如有多个参数的时候需要注意
    //如果只有名字 那么mybatis 只知道顺序 所以报错信息中建议我们使用 arg0 或者  param1  这种方式传递
    //因为没有向 map 一样的键值对 所以我们手动添加上映射关系才可以

    List<User> getUserByParams3(@Param("username") String aaaa,@Param("password") String bbbb);

    //sql中参数名称必须与 实体类 一致

    List<User> gerUserByParams4(User user);

    /*//-----------------------------------------2.15------------------------------------------------*/

    List<User> getAllUsers4ResultMap();

    List<User> getAllUsers4Limit(@Param("a") int a ,@Param("b") int b);
    List<User> getAllUsers4RowBounds(RowBounds rowBounds);
    List<User> getAllUsers4pagehelper();
}

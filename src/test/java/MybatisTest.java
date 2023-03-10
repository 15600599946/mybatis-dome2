import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    @Test
    public void test1() throws IOException {
        String mybatisConfig = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(mybatisConfig);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        List<User> userList = sqlSession.selectList("mapper.UserMapper.getAllUsers");
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException {
        String mybatisConfig = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(mybatisConfig);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList =mapper.getAllUsers();
        System.out.println(userList);
        sqlSession.close();
        }

        @Test
        public void  test03() throws IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
        }
        @Test
        public  void  test04() throws IOException{
        SqlSession sqlSession =MybatisUtils.getSqlSession();
        UserMapper mapper =sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUserByName("'???'");
        System.out.println(user);
        sqlSession.close();
        }
        @Test
        public void  Test05() throws  IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper =sqlSession.getMapper(UserMapper.class);
        int i = mapper.addUser(new User("?????????","123321"));
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
        }
        @Test
        public void Test06() throws  IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession .getMapper(UserMapper.class);
        int i = mapper.addUser(new User(5,"?????????","147741"));
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
        }
        @Test
        public  void  Test07() throws  IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String id = "9,10";
            int i = mapper.deleteUsers(id);
            System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
        }
        @Test
        public void TestParams1(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        System.out.println("=======================map??????");

        Map map = new HashMap();
        map.put("name","???");
        map.put("pwd","123321");
        List<User> userByParams1 = mapper.getUserByParams1(map);
        System.out.println(userByParams1);

        System.out.println("=======================???????????????");

        List<User> userByParams2 = mapper.getUserByParams2("???","123321");
        System.out.println(userByParams2);

        System.out.println("=====================??????????????????");

        List<User> userByParams3 = mapper.getUserByParams3("???","123321");
        System.out.println(userByParams3);

        System.out.println("=======================????????????????????????");
        User user = new User(1,"???","14741");
        List<User> userByParams4 = mapper.gerUserByParams4(user);
        System.out.println(userByParams4);
        }
/*------------------------------------------------2.15--------------------------------------------------*/
        @Test
        public void testResultMap(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper =sqlSession.getMapper(UserMapper.class);
        List<User> allUsers4ResultMap = mapper.getAllUsers4ResultMap();
        System.out.println(allUsers4ResultMap);
    }

    @Test
    public void testlimit(){
        SqlSession sqlSession =MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int pageNo = 3;
        int pageSize = 2;
        int pageIndex =(pageNo-1)*pageSize;
        List<User> allUsers4ResultMap = mapper.getAllUsers4Limit(pageIndex,pageSize);
        System.out.println(allUsers4ResultMap);
    }

    public  void testRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int pageNo = 3;
        int pageSize = 2;
        int pageIndex =(pageNo-1)*pageSize;
        RowBounds rowBounds = new RowBounds(pageIndex,pageSize);
        List<User> allUsers4RowBounds = mapper.getAllUsers4RowBounds(rowBounds);
        System.out.println(allUsers4RowBounds);

    }

    public  void testPageHelper(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int pageNo = 3;
        int paSize = 2;
        PageHelper.startPage(pageNo,paSize);

        List<User> allUsers4RowBounds = mapper.getAllUsers4pagehelper();
        PageInfo<User> pageInfo = new PageInfo<>(allUsers4RowBounds);
        List<User> list = pageInfo.getList();
        for ( User user :list){
        System.out.println(user);
        }
    }
}

import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        List<User> user = mapper.getUserByName("'啊啊啊'");
        System.out.println(user);
        sqlSession.close();
        }
        @Test
        public void  Test05() throws  IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper =sqlSession.getMapper(UserMapper.class);
        int i = mapper.addUser(new User("张婉琳","123321"));
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
        }
        @Test
        public void Test06() throws  IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession .getMapper(UserMapper.class);
        int i = mapper.addUser(new User(5,"张婉琳","147741"));
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
}

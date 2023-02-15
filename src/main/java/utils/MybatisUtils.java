package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

        /*   private static SqlSessionFactory sqlSessionFactory;
        static {
        try {
            String mybatisConfig = "mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(mybatisConfig);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();

    }
    */
        private static ThreadLocal<SqlSession>  threadLocal = new ThreadLocal<>();
        private static  SqlSessionFactory   sqlSessionFactory   = null;

        static{
            InputStream is = null;
        try {
            String mybatisConfig = "mybatis-config.xml";
            is =Resources.getResourceAsStream(mybatisConfig);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        }catch (IOException e){
        e.printStackTrace();
             }
         }

        public static SqlSession getSqlSession(){
        SqlSession sqlSession =threadLocal.get();
        if (sqlSession == null){
        sqlSession = sqlSessionFactory.openSession();
        threadLocal.set(sqlSession);
                }
        return sqlSession;
            }

    public  static  void closeSqlSession(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession !=null){
        sqlSession.close();
        threadLocal.set(null);
                }
            }
        }





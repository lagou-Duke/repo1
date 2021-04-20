package lagou.test;


import lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    @Test
    public void testFindAll() throws IOException {
    /**
     *  查询user测试
     */
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取sessionFactory会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql
        List<User> users = sqlSession.selectList("userMapper.findAll");
        for (User user : users) {
            System.out.println(user);
        }

        // 释放资源
        sqlSession.close();

    }

    /**
     *  新增user测试
     */
    @Test
   public void testSave() throws IOException {
       // 加载核心配置文件
       InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
       //获取sqlSessionFactory工厂对象
       SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
       //获取会话对象
       SqlSession sqlSession = sqlSessionFactory.openSession();

       //执行sql
       User user = new User();
       user.setUsername("jack");
       user.setSex("男");
       user.setBirthday(new Date());
       user.setAddress("中国香港");

       sqlSession.insert("userMapper.saveUser",user);
       // DML语句，手动提交事务
       sqlSession.commit();
       sqlSession.close();
   }


    /**
     *  修改user测试
     */
    @Test
    public void testUpdata() throws IOException {

        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql
        User user = new User();
        user.setId(4);
        user.setUsername("Duke");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("中国香港");

        sqlSession.update("userMapper.updateUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     *  删除user测试
     */
    @Test
    public void textDelete() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql
        sqlSession.delete("userMapper.deleteUser",4);
        sqlSession.commit();
        sqlSession.close();

    }



}

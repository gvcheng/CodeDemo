package test;

import com.gvc.domain.User;
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
    //快速入门测试方法
    public void mybatisTestQuickStart() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行sql,参数:statementId: namespace.id
        List<User> userList = sqlSession.selectList("userMapper.findAll");

        //5.遍历打印结果
        for (User user : userList) {
            System.out.println(user);
        }

        //6.关闭资源
        sqlSession.close();

    }

    @Test
    //测试新增用户
    public void testSave() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        User user = new User();
        user.setUsername("自动提交事务");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("美国夏威夷");

        sqlSession.insert("userMapper.saveUser",user);

        //手动提交事务
//        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    //测试更新用户
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4);
        user.setUsername("马小芳");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("今州");
        sqlSession.update("userMapper.updateUser",user);

        //手动提交事务
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    //测试删除用户
    public void testDelete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("userMapper.deleteUser",4);

        //手动提交事务
        sqlSession.commit();

        sqlSession.close();
    }
}

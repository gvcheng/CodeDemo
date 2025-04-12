package test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gvc.domain.User;
import com.gvc.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    @Test
    //Mapper代理方式测试方法
    public void mybatisTestQuickStart() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //当前返回的是基于UserMapper产生的代理对象，底层为JDK动态代理，实际类型：proxy
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void testFindAllResultMap() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allResultMap = mapper.findAllResultMap();
        for (User user : allResultMap) {
            System.out.println(user);
        }

        sqlSession.close();
    }


    @Test
    //多条件查询方式一
    public void testfindByIdAndUsername1() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findByIdAndUsername1(1,"嵇围城");
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    //多条件查询方式二
    public void testfindByIdAndUsername2() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findByIdAndUsername2(1,"嵇围城");
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    //多条件查询方式三
    public void testfindByIdAndUsername3() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = new User();
        user1.setIdabc(1);
        user1.setUsernameabc("嵇围城");

        User user = mapper.findByIdAndUsername3(user1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    //模糊查询：方式一
    public void testfindByUsername1() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findByUsername1("%嵇%");
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    //模糊查询：方式二
    public void testfindByUsername2() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findByUsername2("%嵇%");
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    //添加用户，返回主键：方式一
    public void testSaveUser1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsernameabc("宫水三叶");
        user.setBirthdayabc(new Date());
        user.setSexabc("女");
        user.setAddressabc("东京");

        System.out.println(user);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.saveUser1(user);
        System.out.println(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    //添加用户，返回主键：方式二
    public void testSaveUser2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsernameabc("汤唯");
        user.setBirthdayabc(new Date());
        user.setSexabc("女");
        user.setAddressabc("浙江杭州");

        System.out.println(user);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.saveUser2(user);
        System.out.println(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    //动态sql的 <if>标签：多条件查询
    public void testfindByIdandUsernameIf() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setIdabc(10);
        user.setUsernameabc("汤唯");

        List<User> userList = mapper.findByIdAndUsernameIf(user);
        for (User user1 : userList) {
            System.out.println(user1);
        }

        sqlSession.close();
    }

    @Test
    //动态sql的 <set>标签：动态更新
    public void testUpdateIf() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsernameabc("Khalil");
        user.setAddressabc("夏威夷");
        user.setIdabc(11);
        mapper.updateIf(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    //动态sql的 <foreach>标签：多值查询 - 集合
    public void testFindByList() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(7);
        idList.add(9);
        idList.add(11);
        List<User> userList = mapper.findByList(idList);
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    //动态sql的 <foreach>标签：多值查询 - 数组
    public void testFindByAraay() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer[] idArray = {5,7,9,10,11} ;

        List<User> userList = mapper.findByArray(idArray);
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    //核心配置文件深入：<plugin>标签 ：pageHelper
    public void testPageHelper() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //设置分页参数：当前页，显示条数
        PageHelper.startPage(1, 5);

        List<User> userList = mapper.findAllResultMap();
        for (User user : userList) {
            System.out.println(user);
        }

        //获取分页相关的其它参数
        PageInfo<User> userPageInfo = new PageInfo<User>(userList);
        System.out.println("总条数：" + userPageInfo.getTotal());
        System.out.println("总页数：" + userPageInfo.getPages());
        System.out.println("当前页：" + userPageInfo.getPageNum());
        System.out.println("每页显示长度：" + userPageInfo.getPageSize());
        System.out.println("是否第一页：" + userPageInfo.isIsFirstPage());
        System.out.println("是否最后一页：" + userPageInfo.isIsLastPage());


        sqlSession.close();
    }

}

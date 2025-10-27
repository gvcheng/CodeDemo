package com.gvc;

import com.gvc.mapper.ArticleMapper;
import com.gvc.mapper.CommentMapper;
import com.gvc.pojo.Article;
import com.gvc.pojo.Comment;
import com.gvc.uitls.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootMybatisDemoApplicationTests {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    void findCommentById() {
        Comment comment = commentMapper.findById(3);
        System.out.println(comment);
    }

    @Autowired
    private ArticleMapper articleMapper;
    @Test
    void findArticleById() {
        Article article = articleMapper.selectByPrimaryKey(1);
        System.out.println(article);
    }

    //redis写入，key:1 - value:mysql数据库中id为1的Article
    @Autowired
    private RedisUtils redisUtils;
    @Test
    void testRedisWrite() {
        redisUtils.set("1", articleMapper.selectByPrimaryKey(1));
        System.out.println("success");
    }
    // 测试读取redis key为1的值
    @Test
    void testRedisRead() {
        Article article = (Article) redisUtils.get("1");
        System.out.println(article);
    }
}

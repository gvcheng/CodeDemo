package com.gvc.test;

import com.gvc.proxy.CglibProxyFactory;
import com.gvc.proxy.JDKProxyFactory;
import com.gvc.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private JDKProxyFactory jdkProxyFactory;
    @Autowired
    private CglibProxyFactory cglibProxyFactory;

    @Test
    public void testTransfer() {
        accountService.transfer("Tom","Jerry",100d);
    }

    //测试JDK动态代理
    @Test
    public void testJDKProxyFactory() {
        //当前返回的是AccountService的代理对象proxy
        AccountService accountServiceJDKProxy = jdkProxyFactory.createAccountServiceJDKProxy();
        //代理对象调用对象的任何方法时，都会执行底层的invoke()方法
        //accountServiceJDKProxy.transfer("Tom","Jerry",100d);
        accountServiceJDKProxy.save();
    }

    //测试Cglib动态代理
    @Test
    public void testCglibProxyFactory() {
        //当前返回的是AccountService的代理对象proxy
        AccountService accountServiceCglibProxy = cglibProxyFactory.createAccountServiceCglibProxy();
        //代理对象调用对象的任何方法时，都会执行底层的invoke()方法
        accountServiceCglibProxy.transfer("Tom","Jerry",100d);
    }
}

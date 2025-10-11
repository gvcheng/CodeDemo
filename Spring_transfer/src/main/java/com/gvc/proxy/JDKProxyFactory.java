package com.gvc.proxy;

import com.gvc.service.AccountService;
import com.gvc.service.impl.AccountServiceImpl;
import com.gvc.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//JDK动态代理工厂类
@Component
public class JDKProxyFactory {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionManager transactionManager;

    /*
        采用jdk动态代理技术生成目标类的代理对象
        ClassLoader loader, :类加载器--借助被代理对象获取
        Class<?>[] interfaces, ：被代理类所需实现的接口
        InvocationHandler h ：代理对象调用接口任意方法，都会执行InvocationHandler内的invoke()方法
    */
    public AccountService createAccountServiceJDKProxy() {
        AccountService accountServiceProxy = (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override           // proxy: 当前代理对象， method: 被调用的目标方法，args: 被调用目标方法用到的的参数
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object result = null;
                try{
                    if(method.getName().equals("transfer")){
                        System.out.println("前置增强。。。");
                        //手动开启事务
                        transactionManager.beginTransaction();
                        //让被代理对象的原方法执行
                        result = method.invoke(accountService, args);
                        //手动提交事务
                        transactionManager.commitTransaction();
                        System.out.println("后置增强。。。");
                    }else {
                        method.invoke(accountService, args);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    //手动回滚事务
                    transactionManager.rollbackTransaction();
                }finally {
                    //手动释放资源
                    transactionManager.releaseTransaction();
                }

                return result;
            }
        });
        return accountServiceProxy;
    }

}

package com.gvc.proxy;

import com.gvc.service.AccountService;
import com.gvc.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/*
* 采用Cglib动态代理对目标类（AccountServiceImpl）方法（transfer）进行动态增强（添加事务）
* */
@Component
public class CglibProxyFactory {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionManager transactionManager;

    public AccountService createAccountServiceCglibProxy(){
        /*
        编写Cglib对应的API返回proxy
        参数1：目标类的字节码对象
        参数2；动作类，当代理对象调用目标对象的原方法时，执行MethodInterceptor中的intercept()方法
        */
        AccountService accountServiceProxy = (AccountService) Enhancer.create(accountService.getClass(), new MethodInterceptor() {
            @Override           //obj: 生成的代理对象，method: 调用的目标方法，args:调用方法的入参，proxy: 代理方法
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                try {
                    transactionManager.beginTransaction();
                    method.invoke(accountService, args);
                    transactionManager.commitTransaction();
                }catch (Exception e){
                    e.printStackTrace();
                    transactionManager.rollbackTransaction();
                }finally {
                    transactionManager.releaseTransaction();
                }
                return null;
            }
        });

        return accountServiceProxy;
    }
}

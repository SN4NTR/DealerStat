package com.company.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ActionListener {

    @Pointcut("execution(* com.company.dao.UserDaoImpl.getAllUsers(..))")
    public void getUsers() {
    }

    @Before("getUsers()")
    public void beforeGetUsers() {
        System.out.println("Trying to get users...");
    }

    @AfterReturning("getUsers()")
    public void afterGetUsers() {
        System.out.println("Users are got!");
    }
}

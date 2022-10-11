package com.atguigu.boot;

import com.atguigu.boot.bean.User;
import com.atguigu.boot.config.MyConfig;
import com.atguigu.boot.config.MyConfig2;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.function.Predicate;

public class TestImport {

    @Test
    public void testImport() {
        /**
         * 这里只导入了 MyConfig.class;
         *  然而实际上同样也导入了 MyConfig2.class, User.class - @Import({MyConfig2.class, User.class})
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        String[] beanNamesForTypeMyConfig2 = context.getBeanNamesForType(MyConfig2.class);
        String[] beanNamesForTypeUser = context.getBeanNamesForType(User.class);
        Assert.assertTrue("@Import注解导入的bean对象名为，全类名", Arrays.stream(beanNamesForTypeMyConfig2).anyMatch(Predicate.isEqual("com.atguigu.boot.config.MyConfig2")));
        Assert.assertTrue("@Import注解导入的bean对象名为，全类名", Arrays.stream(beanNamesForTypeUser).anyMatch(Predicate.isEqual("com.atguigu.boot.bean.User")));
        Assert.assertTrue("@Import注解导入的bean对象名为，全类名", Arrays.stream(beanNamesForTypeUser).anyMatch(Predicate.isEqual("user")));
    }
}

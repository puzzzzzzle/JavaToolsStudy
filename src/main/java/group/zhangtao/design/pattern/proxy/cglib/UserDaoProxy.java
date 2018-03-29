package group.zhangtao.design.pattern.proxy.cglib;

import group.zhangtao.design.pattern.proxy.UserDao;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserDaoProxy {
    private Object userDao;

    public UserDaoProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    public Object getUserDao() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userDao.getClass());
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            Object result = null;
            if (method.getName().equals("save")) {
                if (objects == null || objects[0].equals("")) {
                    System.out.println("null! Have been rejected");
                } else {
                    System.out.println("before!");
                    result = method.invoke(userDao, objects);
                    System.out.println("end!");
                }

            } else {
                result = method.invoke(userDao, objects);
            }
            return result;
        });
        return enhancer.create();
    }

}

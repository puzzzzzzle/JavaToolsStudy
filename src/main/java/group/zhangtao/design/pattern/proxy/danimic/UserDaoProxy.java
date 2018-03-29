package group.zhangtao.design.pattern.proxy.danimic;

import group.zhangtao.design.pattern.proxy.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserDaoProxy  {


    private Object userDao;
    public UserDaoProxy(Object userDao){
        this.userDao=userDao;
    }
    public Object getInstance() {
        return Proxy.newProxyInstance(userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Object result=null;
                    if (method.getName().equals("save")) {
                        if(args==null||args[0].equals("")){
                            System.out.println("null! Have been rejected");
                        }else {
                            System.out.println("before!");
                            result = method.invoke(userDao,args);
                            System.out.println("end!");
                        }

                    }else {
                        result = method.invoke(userDao,args);
                    }
                    return result;
                });
    }


}

package group.zhangtao.design.pattern.proxy;

import group.zhangtao.design.pattern.proxy.statictest.UserDaoProxy;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
        userDaoProxy.save("");
        userDaoProxy.save("tao");
        System.out.println(userDaoProxy.read());

        UserDao userDaoProxy1 =
                (UserDao) new group.zhangtao.design.pattern.proxy.danimic.UserDaoProxy(userDao).getInstance();
        userDaoProxy1.save("");
        userDaoProxy1.save("tao");
        System.out.println(userDaoProxy1.read());

        UserDao userDaoProxy2 =
                (UserDao) new group.zhangtao.design.pattern.proxy.cglib.UserDaoProxy(userDao).getUserDao();
        userDaoProxy2.save("");
        userDaoProxy2.save("tao");
        System.out.println(userDaoProxy2.read());
    }
}

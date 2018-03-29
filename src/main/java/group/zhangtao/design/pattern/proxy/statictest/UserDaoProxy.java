package group.zhangtao.design.pattern.proxy.statictest;

import group.zhangtao.design.pattern.proxy.UserDao;

public class UserDaoProxy implements UserDao{
    private UserDao userDao;
    public UserDaoProxy(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public void save(String msg) {
        if(msg==null||msg.equals("")){
            System.out.println("null! Have been rejected");
        }else {
            System.out.println("before!");
            userDao.save(msg);
            System.out.println("end!");
        }
    }

    @Override
    public String read() {
        return userDao.read();
    }
}

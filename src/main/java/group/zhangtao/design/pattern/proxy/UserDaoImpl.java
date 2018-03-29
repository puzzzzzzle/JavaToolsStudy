package group.zhangtao.design.pattern.proxy;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(String msg) {
        System.out.println("save finish:"+msg);
    }

    @Override
    public String read() {
        return "tao";
    }
}

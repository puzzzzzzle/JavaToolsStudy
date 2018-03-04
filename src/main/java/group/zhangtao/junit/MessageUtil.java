package group.zhangtao.junit;

public class MessageUtil {
    private String massage;

    public MessageUtil(String massage){
        this.massage=massage;
    }

    public String getMassage() {
        System.out.println(massage);
        return massage;
    }
}

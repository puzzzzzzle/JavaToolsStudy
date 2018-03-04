package group.zhangtao.netio.InternetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InternetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("me:" + inetAddress.getHostAddress() + ":" + inetAddress.getHostName());
            InetAddress[] baidu = InetAddress.getAllByName("www.baidu.com");
            Arrays.stream(baidu).forEach((n) -> System.out.println(n.getHostAddress() + ":" + n.getHostName()));
            Arrays.asList(baidu).forEach((n) -> System.out.println(n.getHostAddress() + ":" + n.getHostName()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}

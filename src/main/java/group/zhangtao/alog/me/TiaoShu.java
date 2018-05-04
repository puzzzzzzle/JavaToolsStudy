package group.zhangtao.alog.me;

import java.util.Scanner;

public class TiaoShu {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int m = in.nextInt();
            int doubleSize = 2 * m - 1;

            int result = 0;
            int flag = 0;
            for (int i = 1; i <= n; i++) {
                if (flag < m) {
                    result += -i;
                    flag++;
                    System.out.println(-i);
                } else {
                    if (flag < doubleSize) {
                        flag++;
                    } else {
                        flag = 0;
                    }
                    System.out.println(i);
                    result += i;
                }
            }
            System.out.println(result);
        }
    }
}

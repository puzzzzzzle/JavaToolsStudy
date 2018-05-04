package group.zhangtao.alog.me;

import java.math.BigInteger;
import java.util.Scanner;

public class PaiLieZuHe {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int k = in.nextInt();
            int a = in.nextInt();
            int x = in.nextInt();
            int b = in.nextInt();
            int y = in.nextInt();

            BigInteger result = new BigInteger("0");

            for (int i = 0; i < x; i++) {
                if (a * i > k) {
                    break;
                }
                int bTime = k - a * i;
                if (bTime % b == 0) {
                    int bSize = bTime / b;
                    if (bSize > y) {
                        continue;
                    } else {
                        int count = C(x, i) * C(y, bSize);
                        BigInteger bigInteger = new BigInteger(String.valueOf(count));
                        result = result.add(bigInteger);
                    }
                }
            }

            System.out.println(result.mod(new BigInteger("1000000007")));
        }
    }

    private static int C(int m, int n) {
        return sum(m) / (sum(n) * sum(m - n));
    }

    private static int sum(int x) {
        int result = 1;
        for (int i = x; i > 0; i--) {
            result *= i;
        }
        return result;
    }
}

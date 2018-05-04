package group.zhangtao.alog;

import java.math.BigDecimal;

public class Cycle {
    public static void main(String[] args) {
        jishuPI();
    }

    //    static void cut(int n){
//        BigDecimal y=new BigDecimal(1.0);
//        BigDecimal two = new BigDecimal(2);
//        BigDecimal three = new BigDecimal(3);
//        BigDecimal four = new BigDecimal(4);
//        for(int  i=0;i<=n;i++){
//            BigDecimal temp = two.pow(i).multiply(three).multiply(y);
//            System.out.println("第"+i+"次切割,为正"+(6+6*i)+"边形，圆周率π≈"+temp);
//            y=Math.sqrt(2-Math.sqrt(4-y*y));
//            y = two.min(four.min(y.multiply(y)))
//        }
//
//    }
    static void jishuPI() {
//        BigDecimal sum = new BigDecimal(2);
        double sum = 2;
        int n = 1;
        int m = 3;
        double t = 2;
        while (m<100) {
            t = t * n / m;
            sum = sum + t;
            n++;
            m += 2;
            System.out.println(sum);
        }
    }
}

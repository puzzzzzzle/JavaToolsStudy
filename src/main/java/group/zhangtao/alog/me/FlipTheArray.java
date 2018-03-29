package group.zhangtao.alog.me;

import java.util.Scanner;

/**
 * 反转数组中的几个元素使数组排序
 * 还未修正
 */
public class FlipTheArray {
    private static boolean check(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean calculate(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length - 1; i++) {
            System.arraycopy(a, 0, b, 0, a.length);
            int temp = b[i];
            b[i] = b[i + 1];
            b[i + 1] = temp;
            if (check(b)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] a = new int[size];
        for(int i=0;i<size;i++){
            a[i]= scanner.nextInt();
        }
        if(calculate(a)){
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
    }
    private static boolean function(int[] data){
        for(int t=0; t<data.length-1; t++){
            if(data[t] > data[t+1]){
                if((t = check(data, t)) == -1){
                    return false;
                } else {
                    t--;
                }
            }
        }
        return true;
    }

    private static int check(int[] data, int point){
        boolean flag = point>0;
        for(int t=point; t<data.length-1; t++){
            if(data[t] < data[t+1]){
                if(data[t] < data[point] && data[t+1] > data[point]){
                    return t;
                } else {
                    return -1;
                }
            }
            if(flag && data[t] < data[point-1]){
                return -1;
            }
        }
        return -1;
    }
}

package group.zhangtao.alog.me;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Length {
    public static void main(String[] args) {
//        String temp ="3\n" +
//                "0 0 2 2\n" +
//                "0 2 0 2\n" +
//                "0 1 5 6\n" +
//                "1 6 0 5\n" +
//                "0 0 7 7\n" +
//                "0 3 0 3\n";
//        Scanner scanner = new Scanner(temp);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            int[] group = new int[8];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 8; j++) {
                    group[j] = scanner.nextInt();
                }
                if (calculate(group)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }
    }

    private static boolean calculate(int[] group) {
        List<int[]> points = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            int[] a = new int[]{group[i], group[i + 4]};
            points.add(a);
        }
        List<int[]> lengths = new LinkedList<>();
        for (int[] p : points) {
            int[] mlength = new int[3];
            int i = 0;
            for (int[] q : points) {
                if (q[0]!=p[0]||q[1]!=p[1]) {
                    int length = length(p, q);
                    mlength[i] = length;
                    i++;
                }
            }
            lengths.add(mlength);
        }
        int sh = 0;
        for (int i = 0; i < lengths.size(); i++) {
            if (i == 0) {
                if (lengths.get(i)[0] == lengths.get(i)[1]) {
                    sh = lengths.get(i)[0];
                } else if (lengths.get(i)[0] == lengths.get(i)[2]) {
                    sh = lengths.get(i)[0];
                } else if (lengths.get(i)[1] == lengths.get(i)[2]) {
                    sh = lengths.get(i)[1];
                } else {
                    return false;
                }
            } else {
                if (lengths.get(i)[0] == lengths.get(i)[1]) {
                    if (!(sh == lengths.get(i)[0])) {
                        return false;
                    }
                } else if (lengths.get(i)[0] == lengths.get(i)[2]) {
                    if (!(sh == lengths.get(i)[0])) {
                        return false;
                    }
                } else if (lengths.get(i)[1] == lengths.get(i)[2]) {
                    if (!(sh == lengths.get(i)[1])) {
                        return false;
                    }
                } else {
                    return false;
                }
            }

        }
        return true;
    }

    private static int length(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }
}

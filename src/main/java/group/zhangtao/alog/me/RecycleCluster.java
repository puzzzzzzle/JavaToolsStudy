package group.zhangtao.alog.me;

import java.util.*;

public class RecycleCluster {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                int size = scanner.nextInt();
                int a[][] = new int[4][size];
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < size; i++) {
                        a[j][i] = scanner.nextInt();
                    }
                }
                calculate(a);
            }
        }
    }

    private static void calculate(int a[][]) {
//        int result = 0;
        List<int[]> comp = new LinkedList<>();
        for (int i = 0; i < a[0].length - 1; i++) {
            for (int j = i + 1; j < a[0].length; j++) {
                if (compare(i, j, a)) {
                    comp.add(new int[]{i, j});
                }
            }
        }
        List<Set<Integer>> cluster = cluster(comp, a[0].length);

        int max = cluster.stream().mapToInt(Set::size).max().getAsInt();
        System.out.println(max);
    }

    private static List<Set<Integer>> cluster(List<int[]> comp, int total) {
        List<Set<Integer>> result = new LinkedList<>();

        //初始化簇
        for (int[] temp : comp) {
            Iterator<Set<Integer>> listIterator = result.iterator();
            boolean status = false;
            while (listIterator.hasNext()) {
                Set<Integer> list = listIterator.next();
                if (list.contains(temp[0])) {
                    list.add(temp[1]);
                    status = true;
                } else if (list.contains(temp[1])) {
                    list.add(temp[0]);
                    status = true;
                }
            }
            if (!status) {
                Set<Integer> temp1 = new HashSet<>();
                temp1.add(temp[0]);
                temp1.add(temp[1]);
                result.add(temp1);
            }
        }
        //聚类
        while (result.stream().mapToInt(Set::size).sum() != total) {
            Iterator<Set<Integer>> listIterator = result.iterator();
            while (listIterator.hasNext()) {
                Set<Integer> list = listIterator.next();

                Iterator<Set<Integer>> innerIterator = result.iterator();
                while (innerIterator.hasNext()) {
                    Set<Integer> inner = listIterator.next();
                    if (list != inner) {
                        for (Integer i : inner) {
                            if (list.contains(i)) {
                                list.addAll(inner);
                                inner.remove(inner);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    private static boolean compare(int i, int j, int a[][]) {
//        if (a[0][i] > a[0][j]) {
//            if (a[1][i] > a[1][j]) {
//                if (a[2][j] > a[0][i] && a[3][j] > a[1][i]) {
//                    return true;
//                }
//            } else if (a[1][i] < a[1][j]) {
//                if (a[1][j] < a[3][i] && a[2][j] > a[0][i]) {
//                    return true;
//                }
//            }
//        } else if (a[0][i] < a[0][j]) {
//            if (a[1][i] < a[1][j]) {
//                if (a[2][i] > a[0][j] && a[3][i] > a[1][j]) {
//                    return true;
//                }
//            } else if (a[1][i] > a[1][j]) {
//                if (a[1][i] < a[3][j] && a[2][i] > a[0][j]) {
//                    return true;
//                }
//            }
//        }
        float lx = Math.abs((a[0][i] + a[2][i]) / 2 - (a[0][j] + a[2][j]) / 2);
        float ly = Math.abs((a[1][i] + a[3][i]) / 2 - (a[1][j] + a[3][j]) / 2);
        float sax = a[2][i] - a[0][i];
        float say = a[3][i] - a[1][i];
        float sbx = a[2][j] - a[0][j];
        float sby = a[3][j] - a[1][j];
        if (lx <= (sax + sbx) / 2 && ly <= (say + sby) / 2) {
            return true;
        }
        return false;
    }

}

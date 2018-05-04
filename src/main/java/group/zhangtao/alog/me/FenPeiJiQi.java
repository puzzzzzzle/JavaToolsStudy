package group.zhangtao.alog.me;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FenPeiJiQi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int m = in.nextInt();
            List<int[]> mechines = new LinkedList<>();
            List<int[]> tasks = new LinkedList<>();
            List<int[]> finished = new LinkedList<>();
            IntStream.range(0, n).forEach(i -> {
                int[] temp = new int[2];
                temp[0] = in.nextInt();
                temp[1] = in.nextInt();
                mechines.add(temp);

            });
            IntStream.range(0, m).forEach(i -> {
                int[] temp = new int[2];
                temp[0] = in.nextInt();
                temp[1] = in.nextInt();
                tasks.add(temp);
            });

            mechines.sort((o1, o2) -> o2[1] - o1[1]);
            tasks.sort((o1, o2) -> o2[1] - o1[1]);

            Iterator<int[]> ti = tasks.iterator();
            while (ti.hasNext()) {
                int[] task = ti.next();
                Iterator<int[]> mi = mechines.iterator();
                while (mi.hasNext()) {
                    int[] mechine = mi.next();
                    if (mechine[0] >= task[0] && mechine[1] >= task[1]) {
                        ti.remove();
                        mi.remove();
                        finished.add(task);
                        break;
                    }
                }
            }
            System.out.println(finished.size() + " " +
                    finished.stream()
                            .mapToInt(mapper -> 200 * mapper[0] + 3 * mapper[1])
                            .sum());
        }
    }
}

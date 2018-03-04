package group.zhangtao.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MForkJoinTask extends RecursiveTask<Integer> {
    public int offset = 10;
    private int start;
    private int end;
    private int workTime;
    private List<Integer> works;

    public MForkJoinTask(int start1, int end1, List<Integer> works1, int workTime) {
        this.start = start1;
        this.end = end1;
        this.works = works1;
        this.workTime = workTime;
    }

    @Override
    protected Integer compute() {
        Integer sum = 0;
        if (end - start < offset) {
            for (int i = start; i < end; i++) {
                if (workTime > 0) {
                    try {
                        //模拟工作耗时
                        Thread.sleep(workTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                sum += works.get(i);
            }
        } else {
            int medium = (start + end) / 2;
            MForkJoinTask left = new MForkJoinTask(start, medium, works, workTime);
            MForkJoinTask right = new MForkJoinTask(medium, end, works, workTime);

            left.fork();
            right.fork();
            sum = left.join() + right.join();

        }

        return sum;
    }

}

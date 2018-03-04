package group.zhangtao.grammer.trycash;

public class Test {
    //预热次数
    private int beforeCount = 1000000;
    //测试次数
    private int runCount = 50000000;

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println("预热：");
        t.inTest(t.beforeCount);
        t.outTest(t.beforeCount);

        System.out.println("正式：");
        t.inTest(t.runCount);
        t.outTest(t.runCount);

    }

    private void inTest(int times) {
        System.out.print("in : ");
        long time = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }

    private void outTest(int times) {
        System.out.print("out : ");
        long time = System.currentTimeMillis();
        try {
            for (int i = 0; i < times; i++) {
                Thread.sleep(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}

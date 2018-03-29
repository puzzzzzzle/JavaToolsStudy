package group.zhangtao.alog.me;

import org.junit.Test;

public class MJumpFloor {
    public static int JumpFloor(int target) {
        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        int one = 1;
        int two = 2;
        int result = 0;
        for (int i = 2; i < target; i++) {
            result = one + two;
            one = two;
            two = result;
        }
        return result;
    }

    public int JumpFloorII(int target) {
        if(target==0){
            return 0;
        }
        int size = target+1;
        int[] result = new int[size];
        result[0] = 1;
        result[1] = 1;
        for (int i=0;i<size;i++){
            for (int j=0;j<i;j++){
                result[i]+=result[j];
            }
        }
        return result[size];
    }

    @Test
    public void test() {
        int []ints=new int[20];
        for (int i = 0; i < 20; i++) {
//            System.out.println(i + ":" + JumpFloor(i));
            System.out.println(ints[i]);
        }

    }
}

package group.zhangtao.alog.me;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CompareArray {
    //比较排序后的数组,从小到大
    private static boolean compareSorted(int a [],int b[]){
        int i=0,j=0;
        while (i<a.length&&j<b.length){
            if(a[i]==b[j]){
                return true;
            }else if(a[i]>b[j]){
                j++;
            }else {
                i++;
            }
        }
        return false;
    }
    @Test
    public void test(){
        Random random = new Random();
        int [] a=random.ints(0,30).limit(5).distinct().sorted().toArray();
        int [] b=random.ints(0,30).limit(7).distinct().sorted().toArray();
        System.out.printf("a:%s\nb:%s",
                IntStream.of(a).collect(StringBuilder::new,(sb,i)->sb.append(i).append(","), StringBuilder::append).toString(),
                IntStream.of(b).collect(StringBuilder::new, (sb,i)->sb.append(i).append(","), StringBuilder::append).toString());
        System.out.println(compareSorted(a,b));
    }
}

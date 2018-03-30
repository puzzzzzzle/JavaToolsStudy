package group.zhangtao.grammer;

import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MBigNumber {
    public static void main(String[] args) {
        System.out.println("1\n1");
        Stream.generate(new Supplier<BigInteger>() {
            BigInteger i =new BigInteger("1");
            BigInteger j= new BigInteger("1");
            @Override
            public BigInteger get() {
                BigInteger temp = i.add(j);
                i = j;
                j = temp;
                return j;
            }
        }).parallel().forEach(i-> System.out.println(i.toString()));
    }
}

package group.zhangtao.grammer;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MBigNumber {
    public static void main(String[] args) throws FileNotFoundException {
        try (
                PrintWriter normal = new PrintWriter(
                        new BufferedOutputStream(
                                new FileOutputStream("normal")
                        )
                );
                PrintWriter parra = new PrintWriter(
                        new BufferedOutputStream(
                                new FileOutputStream("parra")
                        )
                )
        ) {
            int count = 1000;

            int times = 55;
            int preapare = 5;
            long normalT = 0;
            long parT = 0;
            for (int j = 0; j < times; j++) {
                final StringBuffer s   = new StringBuffer();

                long start = System.nanoTime();
//                getStream().limit(count).forEach(i -> normal.append(i.toString()).append("\n"));
                getStream().limit(count).forEach(i -> s.append(i.toString()).append("\n"));


                final StringBuffer s1   = new StringBuffer();

                long start1 = System.nanoTime();
//                getStream().parallel().limit(count).collect(Collectors.toList()).forEach(i -> parra.append(i.toString()).append("\n"));
                getStream().parallel().limit(count).collect(Collectors.toList()).forEach(i -> s1.append(i.toString()).append("\n"));
                long start2 = System.nanoTime();


                if (j < preapare) {
                    continue;
                } else {
                    normalT += start1 - start;
                    parT += start2 - start1;
                }
                System.out.println(start1 - start);
                System.out.println(start2 - start1);
                System.out.println();
            }
            System.out.println(normalT);
            System.out.println(parT);

        }
    }

    private static Stream<BigInteger> getStream() {
        return Stream.generate(new Supplier<BigInteger>() {
            BigInteger i = new BigInteger("1");
            BigInteger j = new BigInteger("1");

            @Override
            public BigInteger get() {
                BigInteger temp = i.add(j);
                i = j;
                j = temp;
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                return j;
            }
        });
    }
}

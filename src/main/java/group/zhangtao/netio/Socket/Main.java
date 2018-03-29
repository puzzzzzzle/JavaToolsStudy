package group.zhangtao.netio.Socket;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


//        List<String>[] ls = new ArrayList[10];
////        List<String>[] ls = new ArrayList<String>[10];
//        ls[0]=new ArrayList<>();
//        ls[1]=new ArrayList<>();
//        ls[0].add("5");
//        ls[1].add("8");
////        Files;
//        Length main = new Length();
////        main.dataOutput();
//        main.dataInput();
//        Class c = main.getClass();
//        try {
//            Length o = (Length) c.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        System.out.println(power(2,5));
        System.out.println("输入模");
        Scanner scanner = new Scanner(System.in);
        int j = Integer.parseInt(scanner.nextLine());
        int p = power(2, j);
        System.out.println("输入数字");
        while (scanner.hasNextLine()) {
            int i = Integer.parseInt(scanner.nextLine());
            System.out.println("摸" + j + "除" + p + "：" + i / p);
            int temp = i >> j;
            System.out.println("摸" + j + "右移" + j + "位：" + temp);
            System.out.println("输入数字");
        }
//        System.out.println(System.currentTimeMillis());
    }

    public static int power(int x, int n) {
        int res = x;
        for (int i = 1; i < n; i++) {
            res *= x;
        }
        return res;
    }

    private void fileStream(int i) {
        String[] strings = new String[i];
        int j = 10;
        String[] strings1 = new String[j];
        try (
                FileInputStream in = new FileInputStream(
                        new File("data"));
                InputStreamReader inputStreamReader = new InputStreamReader(
                        new BufferedInputStream(in)
                )
        ) {
//            byte bytes[] = new byte[10];
//            in.read(bytes);
//            for (byte b:bytes
//                 ) {
//                System.out.println("b = " + b);
//            }

//            in.reset();
            char chars[] = new char[10];

            inputStreamReader.read(chars);
            String s = new String(chars);
            System.out.println("s = " + s);
            for (char c : chars
                    ) {
                System.out.println("c = " + c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataOutput() {
        try (
                DataOutputStream dataOutputStream = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(
                                        new File("data")
                                )
                        )
                )
        ) {
            double d1 = 2.33;
            int i1 = 233;
            char c1 = '2';
            dataOutputStream.writeInt(i1);
            dataOutputStream.writeDouble(d1);
            dataOutputStream.writeDouble(c1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataInput() {
        try (
                DataInputStream dataInputStream = new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream("data")
                        )
                )
        ) {
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readDouble());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

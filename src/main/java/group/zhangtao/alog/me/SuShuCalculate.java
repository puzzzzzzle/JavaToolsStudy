package group.zhangtao.alog.me;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuShuCalculate {


    static List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//		System.out.println("请输入一个正整数：");

        while (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            getSuyinzi(nextInt);
            print(list);
            list.clear();
        }

		/*int n = 16;
	    System.out.println(Math.sqrt(n));//开平方
	    System.out.println(Math.pow(2, 3));//pow(a, b):a的b次方
	    System.out.println(Math.abs(-4));//求绝对值
	    System.out.println(Math.log10(100));//log
*/
    }

    public static void getSuyinzi(int nextint) {
        boolean flag = false;
        for (int i = 2; i < nextint; i++) {
            if (nextint % i == 0) {
                flag = true;
                nextint = nextint / i;
                list.add(i);
                getSuyinzi(nextint);
                break;
            }
        }
        if (!flag) {
            list.add(nextint);
        }
    }

    public static void print(List<Integer> list) {
        List<Integer> list2 = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            int n = 0;
            for (int j = 1; j < 1000000; j *= 10) {
                n += 1;
                if (list.get(i) / j >= 10) {
                    continue;
                } else {
                    list2.add(n);
                    break;
                }
            }
        }
        int length = 0;
        String print = "";
        for (int i = 0; i < list.size(); i++) {
            length += list2.get(i);
            print += list.get(i) + "*";
        }
        print = print.substring(0, print.length() - 1);
        length = length * 3 + list.size() - 1;
        char[] char1 = new char[length];
        char[] char2 = new char[length];
        char[] char3 = new char[length];
        char[] char4 = new char[length];
        char[] char5 = new char[length];
        int charxb = 0;
        for (int i = 0; i < print.length(); i++) {
            char e1 = print.charAt(i);
            String e = String.valueOf(e1);
            if ("*".equals(e)) {
                char1[charxb] = ' ';
                char2[charxb] = ' ';
                char3[charxb] = '*';
                char4[charxb] = ' ';
                char5[charxb] = ' ';
                charxb += 1;
            } else {
                if ("0".equals(e)) {
                    //	' - '
                    //	'| |'
                    //	'   '
                    //	'| |'
                    //	' - '
                    char1[charxb] = ' ';
                    char2[charxb] = '|';
                    char3[charxb] = ' ';
                    char4[charxb] = '|';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = '-';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = ' ';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = '-';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = '|';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                } else if ("1".equals(e)) {
                    //	'   '
                    //	'  |'
                    //	'   '
                    //	'  |'
                    //	'   '
                    char1[charxb] = ' ';
                    char2[charxb] = ' ';
                    char3[charxb] = ' ';
                    char4[charxb] = ' ';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = ' ';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = ' ';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = ' ';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = '|';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                } else if ("2".equals(e)) {
                    //	' - '
                    //	'  |'
                    //	' - '
                    //	'|  '
                    //	' - '
                    char1[charxb] = ' ';
                    char2[charxb] = ' ';
                    char3[charxb] = ' ';
                    char4[charxb] = '|';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = '-';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = '-';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = '-';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = '|';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = ' ';
                    char5[charxb + 2] = ' ';
                } else if ("3".equals(e)) {
                    //	' - '
                    //	'  |'
                    //	' - '
                    //	'  |'
                    //	' - '
                    char1[charxb] = ' ';
                    char2[charxb] = ' ';
                    char3[charxb] = ' ';
                    char4[charxb] = ' ';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = '-';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = '-';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = '-';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = '|';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                } else if ("4".equals(e)) {
                    //	'   '
                    //	'| |'
                    //	' - '
                    //	'  |'
                    //	'   '
                    char1[charxb] = ' ';
                    char2[charxb] = '|';
                    char3[charxb] = ' ';
                    char4[charxb] = ' ';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = ' ';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = '-';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = ' ';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = '|';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                } else if ("5".equals(e)) {
                    //	' - '
                    //	'|  '
                    //	' - '
                    //	'  |'
                    //	' - '
                    char1[charxb] = ' ';
                    char2[charxb] = '|';
                    char3[charxb] = ' ';
                    char4[charxb] = ' ';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = '-';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = '-';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = '-';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = ' ';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                } else if ("6".equals(e)) {
                    //	' - '
                    //	'|  '
                    //	' - '
                    //	'| |'
                    //	' - '
                    char1[charxb] = ' ';
                    char2[charxb] = '|';
                    char3[charxb] = ' ';
                    char4[charxb] = '|';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = '-';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = '-';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = '-';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = ' ';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                } else if ("7".equals(e)) {
                    //	' - '
                    //	'  |'
                    //	'   '
                    //	'  |'
                    //	'   '
                    char1[charxb] = ' ';
                    char2[charxb] = ' ';
                    char3[charxb] = ' ';
                    char4[charxb] = ' ';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = '-';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = ' ';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = ' ';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = '|';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                } else if ("8".equals(e)) {
                    //	' - '
                    //	'| |'
                    //	' - '
                    //	'| |'
                    //	' - '
                    char1[charxb] = ' ';
                    char2[charxb] = '|';
                    char3[charxb] = ' ';
                    char4[charxb] = '|';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = '-';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = '-';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = '-';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = '|';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                } else if ("9".equals(e)) {
                    //	' - '
                    //	'| |'
                    //	' - '
                    //	'  |'
                    //	' - '
                    char1[charxb] = ' ';
                    char2[charxb] = '|';
                    char3[charxb] = ' ';
                    char4[charxb] = ' ';
                    char5[charxb] = ' ';

                    char1[charxb + 1] = '-';
                    char2[charxb + 1] = ' ';
                    char3[charxb + 1] = '-';
                    char4[charxb + 1] = ' ';
                    char5[charxb + 1] = '-';

                    char1[charxb + 2] = ' ';
                    char2[charxb + 2] = '|';
                    char3[charxb + 2] = ' ';
                    char4[charxb + 2] = '|';
                    char5[charxb + 2] = ' ';
                }
                charxb += 3;
            }
        }
        String valueOf1 = String.valueOf(char1);
        String valueOf2 = String.valueOf(char2);
        String valueOf3 = String.valueOf(char3);
        String valueOf4 = String.valueOf(char4);
        String valueOf5 = String.valueOf(char5);

        System.out.println(valueOf1);
        System.out.println(valueOf2);
        System.out.println(valueOf3);
        System.out.println(valueOf4);
        System.out.println(valueOf5);
    }

}

package group.zhangtao.nio;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Temp {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<Integer> list = new ArrayList<>();

        list.add(12);
        list.add(12);

//这里直接添加会报错
//        list.add("a");
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        add.invoke(list, "kl");
        System.out.println(list.get(0)+list.get(1));
        Object o = list.get(2);
//        Integer o1 = list.get(2);

        System.out.println(o);
//        System.out.println(list.get(2)+list.get(1));
        System.out.println(list);
    }
}

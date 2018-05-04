package group.zhangtao.grammer;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestGeneric {

    @Test
    public void test01() {
//        new A<If<Child>>();     //报错
        new A<If<Father>>();    //不报错
        new A<If<GrandFather>>();   //不报错

//        new B<If<Child>>();     //报错
//        new B<If<GrandFather>>();   //报错
        new B<If<Father>>();    //不报错

        new C<If<Father>>();    //不报错
        new C<If<Child>>();     //不报错
//        new C<If<GrandFather>>();   //报错

        List<? extends Father> eFathers = new LinkedList<>();
        //        eFathers.add(new Child())

        List<? super Father> sFathers = new LinkedList<>();
        sFathers.add(new Child("child"));
        sFathers.add(new Father("father"));
        sFathers.add(new Child("child"));
        sFathers.add(new Father("father"));
        sFathers.add(new Child("child"));
        sFathers.add(new Father("father"));
        sFathers.forEach(System.out::println);
//        sFathers.add(new GrandFather("GrandFather"));
        List<Father> fatherList = new LinkedList<>();
        adds(fatherList,new Father("father"));
        adds(fatherList,new Child("child"));
//        adds(fatherList,new GrandFather("GrandFather"));

//        sayObjectHello(sFathers);

        List<Child> children = new LinkedList<>();
        children.add(new Child("child"));
        children.add(new Child("child"));
        children.add(new Child("child"));
        children.add(new Child("child"));
        children.add(new Child("child"));
        sayHelloFromFather(children);
        List<Father> fathers = new LinkedList<>();
        fathers.add(new Father("father"));
        fathers.add(new Father("father"));
        fathers.add(new Father("father"));
        fathers.add(new Father("father"));
        fathers.add(new Father("father"));
        sayHelloFromFather(fathers);

        List<GrandFather> grandFathers = new LinkedList<>();
        grandFathers.add(new GrandFather("GrandFather"));
        grandFathers.add(new GrandFather("GrandFather"));
        grandFathers.add(new GrandFather("GrandFather"));
        grandFathers.add(new GrandFather("GrandFather"));
        grandFathers.add(new GrandFather("GrandFather"));
        grandFathers.add(new GrandFather("GrandFather"));
//        sayHelloFromFather(grandFathers);

    }

    public static <T> void adds(List<? super T> list,T item) {
        list.add(item);
//        list.add(new GrandFather("GrandFather"));
    }

    public static void sayHelloFromFather(List<? extends Father> list) {
        list.forEach(e -> {
//            System.out.println(e.sayInChild());
            System.out.println(e.sayInFather());
            System.out.println(e.sayInGrandFather());
        });
    }

    private <T extends  Comparable<T>> T copy(List<T> from, List<T> to) {
        AtomicReference<T> max = new AtomicReference<>();
        from.forEach(f -> {
            int i = 0;
            to.add(f);
            if (max.get() != null) {
                max.set(max.get().compareTo(f) > 0 ? max.get() : f);
            } else {
                max.set(f);
            }
        });
        return max.get();
    }

    @Test
    public void testList() {
        List<MyInt> from =
                IntStream.range(0, 100).boxed().map((integer)->{
                    MyInt myInt = new MyInt();
                    myInt.value = integer;
                    return myInt;
                }).collect(Collectors.toList());
        List<MyInt> to = new LinkedList<>();
        MyInt max = copy(from,to);
        System.out.println(from);
        System.out.println(to);
        System.out.println(max);
    }
//    @Test
//    public void testMyTwoIntList() {
//        List<MyTwoInt> from =
//                IntStream.range(0, 100).boxed().map((integer)->{
//                    MyTwoInt myTwoInt = new MyTwoInt();
//                    myTwoInt.value = integer;
//                    return myTwoInt;
//                }).collect(Collectors.toList());
//        List<MyTwoInt> to = new LinkedList<>();
//        MyTwoInt max = copy(from,to);
//        System.out.println(from);
//        System.out.println(to);
//        System.out.println(max);
//    }
}

class MyInt implements Comparable<MyInt>{
    public int value;
    @Override
    public int compareTo(MyInt o) {
        return value-o.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

class MyTwoInt extends MyInt {
    public int another;
}

class GrandFather {
    public GrandFather(String name) {
        this.name = name;
    }

    public String name;

    @Override
    public String toString() {
        return name + ":  hello i'm" + "GrandFather";
    }

    public String sayInGrandFather() {
        return name + ":  hello from" + "GrandFather";
    }
}

class Father extends GrandFather {
    public Father(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name + ":  hello i'm" + "Father";
    }

    public String sayInFather() {
        return name + ":  hello from" + "Father";
    }
}

class Child extends Father {
    public Child(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name + ":  hello i'm" + "Child";
    }

    public String sayInChild() {
        return name + ":  hello from" + "Child";
    }
}

interface If<T> {
    void doSomething();
}

class A<T extends If<? super Father>> {
}

class B<T extends If<Father>> {
}

class C<T extends If<? extends Father>> {
}

package group.zhangtao.io.stream.study;

public class ClassTest {
    public static void main(String[] args) {
        new B();
    }
}

class A extends Object {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new A();
    }
}

class B extends A {
    public B() {
        System.out.println(this.getClass().getName());
        System.out.println(super.getClass().getName());
        try {
            System.out.println(super.clone().getClass().getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
package group.zhangtao.design.pattern.observer;

public class Main {
    public static void main(String[] args) {

        Subject subject = new Subject();
        new MyObserver(subject);
        new AnotherObserver(subject);
        System.out.println("改变状态：10");
        subject.setState(10);
        System.out.println("改变状态：20");
        subject.setState(20);
    }
}

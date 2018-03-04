package group.zhangtao.design.pattern.observer;

public class AnotherObserver extends Observer {
    public AnotherObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("another observer has received, state : "+subject.getState());
    }
}

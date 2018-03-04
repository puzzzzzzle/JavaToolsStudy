package group.zhangtao.design.pattern.observer;

public class MyObserver extends Observer {
    public MyObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("my observer has received, state: "+ subject.getState() );
    }
}

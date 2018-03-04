package group.zhangtao.design.pattern.observer;

public abstract class Observer {
    protected Subject subject;
    public Observer(Subject subject){
        this.subject = subject;
        subject.attach(this);
    }
    public void update(){
    }
}

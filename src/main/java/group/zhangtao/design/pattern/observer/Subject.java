package group.zhangtao.design.pattern.observer;

import java.util.ArrayList;
import java.util.Arrays;

public class Subject {
    private ArrayList<Observer> observers = new ArrayList<>();

    private int state=0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void notifyAllObservers(){
        observers.forEach(n->n.update());
    }

    public void attach(Observer observer){
        observers.add(observer);
    }
}

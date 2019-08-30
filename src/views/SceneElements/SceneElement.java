package views.SceneElements;

import javafx.scene.Node;
import views.Observer;

import java.util.List;

public abstract class SceneElement implements Observable{
    protected List<Observer> observers;
    public SceneElement(){}
    public Node getField(){
        return null;
    }
    public History getHistory(){return null;}
    public void updateObservers(){
        for (Observer o : observers){
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }
}

package views.SceneElements;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import turtle.Turtle;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurtleDisplay extends SceneElement implements Observable, Observer {
    private Rectangle rectangle;
    private List<Observer> observers;
    private Map<Integer, Turtle> turtles;
    public TurtleDisplay(Map<Integer, Turtle> turtles){
        rectangle = new Rectangle(SlogoView.TURTLEVIEWX, SlogoView.TURTLEVIEWY, SlogoView.TURTLEVIEWWIDTH,
                SlogoView.TURTLEVIEWHEIGHT);
        rectangle.setFill(Color.ANTIQUEWHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        observers = new ArrayList<>();
        this.turtles = turtles;
        //loop to add turtles to the display.
        for (int i = 0; i<turtles.size(); i++) {
        	 turtles.get(i).addObserver(this);
        }
    }
    @Override
    public Group getField(){
        Group retgroup = new Group();
        retgroup.getChildren().add(rectangle);
        rectangle.toBack();
        for (Integer i : turtles.keySet()) {
            retgroup.getChildren().add(turtles.get(i).getImage());
        }
        return retgroup;
    }
    public void updateObservers(){
        for (Observer o : observers){
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }
    public void update(Object o){
        //System.out.print(o.getClass().getTypeName());
        if (o.getClass().getTypeName().equals("javafx.scene.paint.Color")) {
            rectangle.setFill((Color) o);
        }
        else if (o.getClass().getTypeName().equals("turtle.Turtle")){
            updateObservers();
        }
        else{

        }
    }
}

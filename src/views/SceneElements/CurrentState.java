package views.SceneElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import turtle.Turtle;
import views.Observer;
import views.SlogoView;

// pen up or down
// pen thickness
// ID, position, heading

public class CurrentState extends SceneElement implements Observable {
	private VBox vbox;
	private Text turtleInfo;
	private Text text;
	private Text id;
	private Text turtleID;
	private Text turtleHeading;
	private Text turtleX;
	private Text turtleY;
	private Text penSize;
	private Text penUP;
	private Text penColor;
//	private List<Text> info;
	private List<Observer> observers;
	public static final double WRAPBUFFER = 30;
    private ScrollPane pane;
    private List<Text> states;
    private Label stateLabel;
	private Turtle turtle;
	private Map<Integer, Turtle> turtleMap;
	private List<Label> turtleList;
	
	public CurrentState(Map<Integer, Turtle> map) {
		turtleMap = map;
		vbox = new VBox();
		states = new ArrayList<>();
		observers = new ArrayList<>();
		stateLabel = getLabel();
		vbox.getChildren().add(stateLabel);
		text = getText();
		pane = getPane(text);
		vbox = getVbox();
		turtleList = new ArrayList<>();

		//vbox.getChildren().add(pane);
	}
	
	private Label getLabel() {
		Label label = new Label("Current States: (Click for more Info)");
        label.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-width: 1;");
		label.setPrefWidth(SlogoView.STATEWIDTH);;
		label.maxWidth(SlogoView.STATEWIDTH);
		label.setAlignment(Pos.CENTER);;
		return label;
	}
	
	private ScrollPane getPane(Text text) {	
		ScrollPane pane = new ScrollPane(text);
		pane.setLayoutX(SlogoView.STATEX);
		pane.setLayoutY(SlogoView.STATEY);
		pane.setPrefWidth(SlogoView.STATEWIDTH);
        pane.setPrefHeight(SlogoView.STATEHEIGHT);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return pane;
	}
	
	 private Text getText() {
	        Text tex = new Text();
	        tex.setWrappingWidth(SlogoView.CMDHISTORYWIDTH - WRAPBUFFER);
	        tex.setStyle("-fx-background-color: grey;");
	        return tex;
	    }
	 
	private VBox getVbox() {
		vbox.setLayoutX(SlogoView.STATEX);
		vbox.setLayoutY(SlogoView.STATEY);
		vbox.setPrefWidth(SlogoView.STATEWIDTH);
        vbox.setMinHeight(SlogoView.STATEHEIGHT);
        vbox.setPrefHeight(SlogoView.STATEHEIGHT);
        vbox.setPadding(new Insets(1,1,1,1));
        vbox.setSpacing(1);
        return vbox;
	}

	
	public void getTurtleInfo(Map<Integer, Turtle> turtleMap) {
	    vbox.getChildren().removeAll(turtleList);
	    turtleList.clear();
	   for (Integer i : turtleMap.keySet()) {
           Label l = new Label("Turtle: " + i + " is " + turtleMap.get(i).getWhichString());
           l.setStyle("-fx-border-color: black; -fx-border-width: 2;");
           l.setPrefWidth(SlogoView.PALETTEWIDTH - 30);
           if (turtleMap.get(i).isActive()){
               l.setStyle("-fx-background-color: lightgreen;");
           }
           else {
               l.setStyle("-fx-background-color: orangered;");
           }
           l.setOnMouseClicked(e -> popUp(turtleMap.get(i), i));
           turtleList.add(l);

       }
        vbox.getChildren().addAll(turtleList);
		//updateObservers();
	}

	private void popUp(Turtle turtle, int number) {
	    String message = "Turtle: " + number + "\n" +
                "Status: " + turtle.getWhichString() + "\n" +
               "Location: " + "(" + turtle.getRelativeLocation().getX() + " , "
                + turtle.getRelativeLocation().getY() + ")" + "\n"
                + "Heading: " + turtle.getHeading() + "\n" +
                "PenUp Status: " + turtle.isPenUp() + "\n" +
                "-------------------";
		Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
		alert.setTitle("");
		alert.setHeaderText("Turtle " + number + " Information");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			//do stuff
		}
	}

	public void refresh(){
//	    vbox.getChildren().removeAll(turtleList);
//	    vbox.getChildren().addAll(turtleList);
        getTurtleInfo(turtleMap);
    }

//	
//	public void updateStateView() {
//		updateState();
//	}

//	public void updateState() {
//		reset();
//	}
	
	
    public void updateObservers(){
        for (Observer o : observers){
        	System.out.println(o.toString());
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
	    observers.add(o);
    }
	
	@Override
	public VBox getField() {
		return vbox;
	}
//
//	public void reset() {
//		vbox.getChildren().clear();
//		vbox.getChildren().add(stateLabel);
//		info = getTurtleInfo();
//		pane = getPane(info);
//		vbox = getVbox();
//		vbox.getChildren().add(pane);
//	}
	
}

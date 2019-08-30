package views.SceneElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class History extends SceneElement implements Observable{
	private VBox vbox;
	private Text text;
	private List<Observer> observers;
	private ScrollPane scrollPane;
	private Label histLabel;
	private String current;
	public static final double WRAPBUFFER = 30;
	private List<String> commands;
	private List<Label> commandlabels;
	private int pos;
	private Console myConsole;
	public History(){
        vbox = getVbox();
        histLabel = getLabel();
        commandlabels = new ArrayList<>();
        commandlabels.add(histLabel);
        vbox.getChildren().addAll(commandlabels);
        scrollPane = getScrollPane(vbox);
        //scrollPane.setFitToHeight(true);
		commands = new ArrayList<>();
		observers = new ArrayList<>();

	}
    public void setMyConsole(Console c){
	    myConsole = c;
    }
    private Label getLabel() {
	    Label l = new Label("Command History");
        l.setStyle("-fx-border-color: white; -fx-border-width: 3;" +
                "-fx-background-color: black; -fx-text-fill: white");
        l.setMaxWidth(SlogoView.CMDHISTORYWIDTH);
        l.setAlignment(Pos.CENTER);
        //l.setPadding(new Insets(2,2,2,5));
	    return l;
    }

    private ScrollPane getScrollPane(VBox box) {
        ScrollPane returnPane = new ScrollPane(box);
        returnPane.setLayoutX(SlogoView.CMDHISTORYX);
        returnPane.setLayoutY(SlogoView.CMDHISTORYY + histLabel.getHeight());
        returnPane.setPrefWidth(SlogoView.CMDHISTORYWIDTH);
        returnPane.setPrefHeight(SlogoView.CMDHISTORYHEIGHT - histLabel.getHeight());
        returnPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return returnPane;
    }

    private Text getText() {
        Text tex = new Text();
        tex.setWrappingWidth(SlogoView.CMDHISTORYWIDTH - WRAPBUFFER);
        tex.setStyle("-fx-background-color: grey;");
        return tex;
    }


    private VBox getVbox() {
        VBox box = new VBox();
        //box.setLayoutX(SlogoView.CMDHISTORYX);
        //box.setLayoutY(SlogoView.CMDHISTORYY);
        box.setPrefWidth(SlogoView.CMDHISTORYWIDTH);
        //box.setPrefHeight(SlogoView.CMDHISTORYHEIGHT);
        box.setPadding(new Insets(0,0,0,0));
        box.setSpacing(2);
        return box;
    }

    @Override
    public ScrollPane getField(){
	    return scrollPane;
    }
	public void addCommand(String command, boolean send){
        commands.add(command);
        if (send) {
            updateText(command);
        }
        pos = commands.size();
        updateObservers();
        scrollPane.setVvalue(1);

    }
    private void updateText(String command) {
	    Label toAdd = new Label("[" + ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS)
                + "]\n " + command);
        toAdd.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: lightblue;");
        toAdd.setOnMouseClicked(e -> sendCommandToConsole(command));
        toAdd.setPrefWidth(SlogoView.CMDHISTORYWIDTH - WRAPBUFFER);
        vbox.getChildren().add(toAdd);

    }

    private void sendCommandToConsole(String text) {
	    myConsole.getCommand(text);
    }

    public void removeLastCommand(){
	    pos--;
        vbox.getChildren().removeAll(commandlabels);
	    commandlabels.remove(pos);
        vbox.getChildren().addAll(commandlabels);
	    commands.remove(pos);
	    pos--;
    }
    public String getLastCommand() throws EmptyStackException{
        pos--;
	    if (pos < 0){
            pos = 0;
        }
        String s;
	    if (commands.size() != 0) {
	        s = commands.get(pos);
        }
        else {
	        s = "";
        }
        return s;
    }
    public String restoreCommand() throws EmptyStackException{
        pos++;
        if (pos > commands.size()){
            pos--;
        }
        if (pos == commands.size()) {
            return "";
        }
        return commands.get(pos);
    }
    public void clear(){
	    commands.clear();
	    pos = 0;
	    text.setText("");
	    //updateObservers();
    }
    public void updateObservers(){
        for (Observer o : observers){
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
	    observers.add(o);
    }
}

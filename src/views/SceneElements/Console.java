package views.SceneElements;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import turtle.Turtle;
import views.Observer;
import views.SlogoView;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Console extends SceneElement implements Observable{
    private VBox vbox;
    private String currentString = "";
    private String[] passValue;
    private History myHistory;
    private CurrentState myCurrentState;
    private TextArea field;
    private List<Observer> observers;
    private TextField littlefield;
    private CurrentState myState;
    private Palettes myPalette;
	private Turtle turtle;
	private Map<Integer, Turtle> turtleMap;
    public static final double MINITOOLBARHEIGHT = .5 * SlogoView.TOOLBARHEIGHT;
    public static final double VBOXBUFFER = 5;
    public Console(Map<Integer, Turtle> turtleMap){
        this.turtleMap = turtleMap;
        vbox = new VBox();
        vbox.getChildren().addAll(getTextArea(), getToolBar());
        vbox.setSpacing(0);
        vbox.setPadding(new Insets(1,1,3,1));;
        vbox.setLayoutX(SlogoView.CONSOLEX);
        vbox.setLayoutY(SlogoView.CONSOLEY);
        vbox.setMaxWidth(SlogoView.CONSOLEWIDTH);
        vbox.setPrefHeight(SlogoView.CONSOLEHEIGHT);
        vbox.setStyle("-fx-border-color: black; -fx-border-width: 2");
        myHistory = new History();
        myHistory.setMyConsole(this);
        observers = new ArrayList<>();
       // littlefield = new TextField();
//        myState = new CurrentState(turtles.get(0));
//  myPalette = new Palettes();
    }
    @Override
    public Node getField(){
        return vbox;
    }
    public String[] getPassValue() {
        return passValue;
    }
    public ToolBar getToolBar(){
        Button button = new Button("Execute");
        Button clearbutton = new Button("Clear");
        clearbutton.setOnAction(e -> clearHistory());
        button.setOnAction(e -> sendText(true));
        Button forward = new Button("Forward");
        forward.setOnAction(e -> handleForward());
        Button backward = new Button("Backward");
        backward.setOnAction(e->handleBack());
        Button right = new Button("Right turn");
        right.setOnAction(e->handleRight());
        Button left = new Button("Left turn");
        left.setOnAction(e->handleLeft());
        littlefield = new TextField();
        ToolBar toolbar = new ToolBar(
                new Label("Console"),
                new Separator(),
                button,
                new Separator(),
                clearbutton,
                littlefield,
                forward,
                backward,
                left,
                right
        );
        toolbar.setMinSize(SlogoView.CONSOLEWIDTH - VBOXBUFFER, MINITOOLBARHEIGHT);
        return toolbar;
    }

    private void handleForward() {
        field.setText("fd 20");
        sendText(false);
    }
    private void handleBack() {
        field.setText("bk 20");
        sendText(false);
    }
    private void handleRight() {
        field.setText("rt 30");
        sendText(false);
    }
    private void handleLeft() {
        field.setText("lt 30");
        sendText(false);
    }
    private void clearHistory() {
        field.setText("");
        myHistory.clear();
    }
    public void setLittleField(String s){
        littlefield.setText("Return: " + s);
    }
    private void sendText(boolean send){
        try {
            //Refactor this to be "if invalid, do something"
            if (field.getText().equals("")) {
                return;
            }
            field.setStyle("-fx-text-fill: black;");
            currentString = field.getText();
            currentString = cleanText(currentString);
            passValue = currentString.split(" ");
            //StringBuilder temp = new StringBuilder(currentString);
            //System.out.println(temp.toString());
            myHistory.addCommand(currentString, send);
            //System.out.println(currentString);
            myCurrentState.getTurtleInfo(turtleMap);
            field.setText("");
            //passValue = currentString.split(" ");
        } catch (InvalidParameterException e){
            myHistory.removeLastCommand();
            field.setText("Sorry, that's not a valid command");
            field.selectAll();
            field.setStyle("-fx-background-color: red;");

        }
    }
    public History getHistory(){
        return myHistory;
    }
    public CurrentState getState() {
    	return myState;
    }
    public Palettes getPalette() {
    	return myPalette;
    }
    private Label getConsoleLabel(){
        Label consolelabel = new Label("Console");
        return consolelabel;
    }
    private TextArea getTextArea(){
        field = new TextArea();
        field.setMaxWidth(SlogoView.CONSOLEWIDTH);
        field.setPromptText("Enter a SLogo command");
        field.setFocusTraversable(false);
        field.setCursor(Cursor.TEXT);
        field.clipProperty();
        field.setOnKeyPressed(event -> handleText(event.getCode()));
        field.setPadding(new Insets(1,1,1,5));
        field.setMaxWidth(SlogoView.CONSOLEWIDTH - VBOXBUFFER);
        return field;
    }
    private void handleText(KeyCode code){
//        if(code == KeyCode.ENTER) {
//            sendText();
//        }
        if (code == KeyCode.ESCAPE){
            sendText(true);
        }
        else if (code == KeyCode.UP){
            field.setText(myHistory.getLastCommand());
            field.positionCaret(field.getText().length());
        }
        else if (code == KeyCode.DOWN){
            field.setText(myHistory.restoreCommand());
            field.positionCaret(field.getText().length());
        }
        else{
            field.setStyle("-fx-text-fill: black;");
        }
    }

    private String cleanText(String text){
        text = text.toLowerCase();
        text = text.trim();
        text = text.replaceAll("\\s+"," ");
        //text = text.replaceFirst("\\n", "");
        return text;
    }
    public String getCurrentString(){
        return currentString;
    }
    
    public void updateObservers(){
        for (Observer o : observers){
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }

    public void getCommand(String text) {
        field.setText(text);
        sendText(true);
    }

    public void setCurrentState(CurrentState myCurrentState) {
        this.myCurrentState = myCurrentState;
    }
}

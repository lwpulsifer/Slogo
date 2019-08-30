package views.SceneElements;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.Hyperlink;
import treenode.NodeBuilder;
import views.Main;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Toolbar extends SceneElement implements Observable{
    private ToolBar toolbar;
    private List<Observer> observers;
    private ColorPicker picker;
    private ColorPicker newpicker;
    private Hyperlink link;
    public static final File CHINESE = new File("resources.languages/Chinese.properties");
    public static final File ENGLISH = new File("resources.languages/English.properties");
    public static final File FRENCH = new File("resources.languages/French.properties");
    public static final File GERMAN = new File("resources.languages/German.properties");
    public static final File ITALIAN = new File("resources.languages/Italian.properties");
    public static final File PORTUGUESE = new File("resources.languages/Portuguese.properties");
    public static final File RUSSIAN = new File("resources.languages/Russian.properties");
    public static final File SPANISH = new File("resources.languages/Spanish.properties");
    public static final File URDU = new File("resources.languages/Urdu.properties");

    private String url = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
    
    public Toolbar() {
        //The Tool Bar is on the top, so no need to set X and Y values
        link = new Hyperlink("Help");
        link.setOnAction(e -> getLink());
        Button pendown = new Button("Toggle Pen Up/Down");
        pendown.setOnAction(e -> toggleUpDown());
        toolbar = new ToolBar(
                link,
                new Separator(),
                new Label("Choose Background Color:"),
                getColorPicker(),
                new Separator(),
                getFileBox(),
                new Label("Choose Line Color"),
                getNewColorPicker(),
                new Separator(),
                pendown


        );
        toolbar.setMinSize(SlogoView.TOOLBARWIDTH, SlogoView.TOOLBARHEIGHT);
        observers = new ArrayList<>();
    }

    private void toggleUpDown() {
        updateObservers();
    }

    private ComboBox<String> getFileBox(){
        ComboBox<String> ret = new ComboBox<String>(getFileList());
        ret.setValue("Language");
        ret.setOnAction(e->{
            NodeBuilder.changeLanguage(ret.getValue());
        });
        return ret;
    }
    private ObservableList<String> getFileList(){
        ObservableList<String> filelist = FXCollections.observableArrayList("Chinese", "English", "French",
                "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu");

        return filelist;
    }
    private void getLink() {
        Main.openWebPage(url);
    }

    private ColorPicker getColorPicker() {
        picker = new ColorPicker();
        picker.setValue(Color.ANTIQUEWHITE);
        picker.setOnAction(e -> updateObservers());
        picker.setPromptText("Choose Color");
        return picker;
    }
    private ColorPicker getNewColorPicker() {
        newpicker = new ColorPicker();
        newpicker.setValue(Color.BLACK);
        newpicker.setOnAction(e -> updateObservers());
        newpicker.setPromptText("Choose Color");
        return newpicker;
    }

    @Override
    public Node getField(){
        return toolbar;
    }
    public void updateObservers(){
        for (Observer o : observers){
            if (o.getClass().getTypeName().equals("views.SceneElements.TurtleDisplay")) {
                o.update(picker.getValue());
            }
            if (o.getClass().getTypeName().equals("turtle.Turtle")){
                o.update(newpicker.getValue());
            }
            else {
                o.update(new Rectangle());
            }
        }
//        if (done) {
//            for (Observer o : observers) {
//                o.update(link);
//            }
//            done = false;
//        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }
}

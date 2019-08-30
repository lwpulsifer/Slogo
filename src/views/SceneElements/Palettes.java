package views.SceneElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import views.Observer;
import views.SlogoView;

public class Palettes extends SceneElement implements Observable{

	private VBox vbox;
	private Text text;
	private List<Observer> observers;
    private ScrollPane pane;
    private List<Label> palettes;
    private Label paletteLabel;
	
	
	public Palettes() {
		vbox = new VBox();
		palettes = new ArrayList<>();
		observers = new ArrayList<>();
		paletteLabel = getLabel();
		vbox.getChildren().add(paletteLabel);
		vbox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: goldenrod;");
		text = getText();
		pane = getPane(text);
		vbox = getVbox();
		vbox.getChildren().add(pane);

		
	}
	
	private VBox getVbox() {
		vbox.setLayoutX(SlogoView.PALETTEX);
		vbox.setLayoutY(SlogoView.PALETTEY);
		vbox.setPrefWidth(SlogoView.PALETTEWIDTH);
        vbox.setPrefHeight(SlogoView.PALETTEHEIGHT);
        vbox.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        return vbox;
	}
	private Label getLabel() {
		Label label = new Label("My Palette");
		label.setStyle("-fx-border-color: white; -fx-border-width: 3;" +
                "-fx-background-color: black; -fx-text-fill: white");
		label.setPrefWidth(SlogoView.PALETTEWIDTH);;
		label.maxWidth(SlogoView.PALETTEWIDTH);
		label.setAlignment(Pos.CENTER);;
		return label;
	}
	
	private ScrollPane getPane(Text text) {
		pane = new ScrollPane(text);
		pane.setLayoutX(SlogoView.PALETTEX);
		pane.setLayoutY(SlogoView.PALETTEY);
		pane.setPrefWidth(SlogoView.PALETTEWIDTH);
        pane.setPrefHeight(SlogoView.PALETTEHEIGHT);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return pane;
	}
	
	private Text getText() {
		Text tex = new Text();
		tex.setWrappingWidth(SlogoView.CMDHISTORYWIDTH );
        tex.setStyle("-fx-background-color: grey;");
        return tex;
	}


	public void updatePaletteView(Map<String, String> paletteMap) {
		updatePalette(paletteMap);
	}
	public void updatePalette(Map<String, String> paletteMap) {
		vbox.getChildren().removeAll(palettes);
		palettes.clear();
		for (String key : paletteMap.keySet()) {
			Label l = new Label(key + " : " + paletteMap.get(key));
//			l.setPadding(new Insets(1, 1, 1, 5));
			palettes.add(l);
		}
		vbox.getChildren().addAll(palettes);
	}

	
    public void updateObservers(){
        for (Observer o : observers){
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
}

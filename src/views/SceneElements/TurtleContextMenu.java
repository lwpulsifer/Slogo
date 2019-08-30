package views.SceneElements;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import turtle.Turtle;
import views.Observer;

//Needs to be deleted.

public class TurtleContextMenu extends SceneElement implements Observable, Observer  {

	private Object contextMenu ;

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	public TurtleContextMenu(Turtle turtle) {
		this.contextMenu = getTurtleFacts(turtle);
		
	}
	
	private ContextMenu getTurtleFacts(Turtle turtle) {
		ContextMenu contextMenu = new ContextMenu();
		TextField position = new TextField();
		TextField heading = new TextField();
		TextField pen = new TextField();
		TextField pencolor = new TextField();
		TextField penthickness = new TextField();
		
		position.setText(Double.toString(turtle.getLocation().getX())+ " , " + Double.toString(turtle.getLocation().getY()));
		heading.setText(Double.toString(turtle.getHeading()));
		if(turtle.isShowing() == 1) {
			
		}
		//pen.setText();
		
		//contextMenu.getItems().addAll(position, heading, pen, pencolor, penthickness);
		return null;
		
	}
	
	
	
	
	
}

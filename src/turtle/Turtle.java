package turtle;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import views.Observer;
import views.SceneElements.CurrentState;
import views.SceneElements.Observable;
import views.SlogoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
*
* Turtle holds knowledge about themselves that can be
* used for display.
* 
*/
public class Turtle implements Observable, Observer{
	private Point2D currentpos;
	private double speed;
	private double heading;
	private ImageView turtleview;
	private Line line;
	private ArrayList<Line> lines;
	private Color lineColor = Color.BLACK;
	private boolean isShowing;
	public static final double initHeading = 0;
    private double oldHeading;
    private CurrentState myState;


    private boolean myPenUp;
	private Canvas myCanvas;
	public static final double TURTLESIZE = 50;
	private List<Observer> observers;
    public static final double BASEX = SlogoView.TURTLEVIEWX + 1.0 / 2 * SlogoView.TURTLEVIEWWIDTH
            - .5 * TURTLESIZE;
    public static final double BASEY = SlogoView.TURTLEVIEWY + 1.0 / 2 * SlogoView.TURTLEVIEWHEIGHT
            - .5 * TURTLESIZE;
    public static final double TURTLESIZEHALF = TURTLESIZE * 0.5;
	public static final double CMDBUFF = 60;
	public static final double FULLDEGREES = 360;
    public static final Point2D originalLocation = new Point2D(BASEX, BASEY);
    
    
    public Map<Integer, Color> turtleColorMap = new HashMap<Integer, Color>();
    public Map<Integer, Shape> turtleShapeMap = new HashMap<Integer, Shape>();
    public int turtleId;
    public static final String INACTIVE = "turtle.png";
    public static final String ACTIVE = "activeturtle.png";
    public static final String ACT = "ACTIVE";
    public static final String INACT = "INACTIVE";
	private String currentActive = INACTIVE;
	private String whichString = "INACTIVE";

	public String getCurrentActive() {
		return currentActive;
	}

	public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        swapImage();
        //System.out.println("Swapped");
    }

    private boolean isActive = true;
    public double penSize;
    public Color penColor;
    public Shape turtleShape;
    
	/**
	 * Variety of getters and setters used to access Information in actors, for
	 * update and display
	 **/
	public Turtle() {
		this(new Point2D(SlogoView.TURTLEVIEWX + 1.0 / 2 * SlogoView.TURTLEVIEWWIDTH
                - 25,SlogoView.TURTLEVIEWY + 1.0 / 2 * SlogoView.TURTLEVIEWHEIGHT
                - 25), 0);
	}


	public Turtle(Point2D currentpos, double speed)
	{
	    observers = new ArrayList<>();
		this.currentpos = currentpos;
		this.speed = speed;
		Image turtle = new Image(currentActive, TURTLESIZE,TURTLESIZE,true,true);
		turtleview = new ImageView(turtle);
		turtleview.setLayoutX(this.currentpos.getX());
		turtleview.setLayoutY(this.currentpos.getY());
		turtleview.setOnMouseClicked(e -> toggleActive());
		line = new Line();
		lines = new ArrayList<>();
		myPenUp = false;
		isShowing = true;
		isActive = false;
		turtleId = turtleColorMap.size() + 1;
		penSize = 2;
		penColor = Color.BLACK;
		turtleShape = new Rectangle(SlogoView.TURTLEVIEWX, SlogoView.TURTLEVIEWY, SlogoView.TURTLEVIEWWIDTH,
                SlogoView.TURTLEVIEWHEIGHT);

	}
    public void setState(CurrentState c){
	    myState = c;
    }
    private void toggleActive() {
	     isActive = !isActive;
	     swapImage();
    }

    private void swapImage() {
	    if (isActive){
	        currentActive = ACTIVE;
	        whichString = "ACTIVE";
        }
        else {
	        currentActive = INACTIVE;
	        whichString = "INACTIVE";
        }
        Image turtle = new Image(currentActive, TURTLESIZE,TURTLESIZE,true,true);
        //turtleview = new ImageView(turtle);
        turtleview.setImage(turtle);
//        turtleview.setLayoutX(this.currentpos.getX());
//        turtleview.setLayoutY(this.currentpos.getY());
//        turtleview.setOnMouseClicked(e -> toggleActive());
        //updateObservers();
        //if (myState != null)
            myState.refresh();
    }

    /**
	 * Point representing the current currentpos of the actor
	 * 
	 * @return Point currentpos
	 */
	public Point2D getLocation()
	{
		return currentpos;
	}
	public ImageView getImage(){return turtleview;}

	public void setAbsoluteLocation(Point2D newpos){
	    setLocation(new Point2D(getOriginalLocation().getX() + newpos.getX(), getOriginalLocation().getY() +
                newpos.getY()));
    }
    public Point2D getRelativeLocation(){
	    return new Point2D(getLocation().getX() - getOriginalLocation().getX(),
                -1 * getLocation().getY() + getOriginalLocation().getY());
    }
    public double getOldHeading() {
        return oldHeading;
    }
	public void setLocation(Point2D newpos)
	{
		if ((newpos.getX() < SlogoView.TURTLEVIEWX) || (newpos.getX() > (SlogoView.TURTLEVIEWX + SlogoView.TURTLEVIEWWIDTH - TURTLESIZEHALF) || (newpos.getY() < SlogoView.TURTLEVIEWY) || (newpos.getY() > (SlogoView.TURTLEVIEWY - CMDBUFF + SlogoView.TURTLEVIEWHEIGHT + TURTLESIZEHALF))))
			return;
        addLine(newpos);
        currentpos = newpos;
		turtleview.setLayoutX(this.currentpos.getX());
		turtleview.setLayoutY(this.currentpos.getY());
		//updateObservers();
	}
    private void setRotate(double degrees){
	    turtleview.setRotate(-1 * degrees);
    }
    private void addLine(Point2D newpos) {
        if (!myPenUp) {
            Line l = new Line();
            l.setStroke(lineColor);
            l.setStrokeWidth(2);
            l.setStartX(currentpos.getX() + TURTLESIZEHALF);
            l.setStartY(currentpos.getY() + TURTLESIZEHALF);
            l.setEndX(newpos.getX() + TURTLESIZEHALF);
            l.setEndY(newpos.getY() + TURTLESIZEHALF);
            lines.add(l);
            //System.out.println(lineColor);
        }
    }

    public List<Line> getLines(){
	    return lines;
    }
    public Point2D getOriginalLocation(){
	    return originalLocation;
    }
	/**
	 * An int representing the speed of the actor as it updates on the screen
	 * 
	 */
	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double s)
	{
		speed = s;
	}

	public double getHeading()
	{
		return heading;
	}

	public double isPenDown() {
		if (!myPenUp) {
			return 1.0;
		}else {
			return 0.0;
		}
	}
	public boolean isPenUp() {
		if (myPenUp) {
			return true;
		} return false;
	}
	public void setHeading(double heading)
	{
	    oldHeading = this.heading;
	    this.heading = heading % FULLDEGREES;
	    setRotate(heading);
	}
	
	public void rotate(double angle) {
		setHeading(heading + angle);
	}
	
	public void penUp() {
		myPenUp = true;
	}
	
	public void penDown() {
		myPenUp = false;
	}
	
	public Image show() {
		isShowing = true;
		turtleview.setOpacity(1);
		return turtleview.getImage();
	}
	
	public void hide() {
		isShowing = false;
		turtleview.setOpacity(0);
	}
	
	public double isShowing() {
		if (isShowing) {
			return 1.0;
		}else {
			return 0.0;
		}
	}

	
	public void clear(){
	    lines.clear();
	    setHeading(initHeading);
	    //updateObservers();
    }
	public void reset() {
		myCanvas.getGraphicsContext2D().clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
	}
	
	public void addColor(int index, Color color) {
		turtleColorMap.put(index , color);
	}
	
	public int getId() {
		return turtleId;
	}
	
	public void setPenSize(double newPenSize) {
		penSize = newPenSize;
	}
	public double getPenSize() {
		return penSize;
	}

	public void setPenColor(double newPenColor) {
		penColor = turtleColorMap.get(newPenColor);
	}
	public double getPenColor() {
		for (int n = 0; n < turtleColorMap.size(); n++) {
			if (turtleColorMap.get(n).equals(penColor)) {
				return (double) n;
			}
		}
		return 0;
	}
	public String penColor(){
		return penColor.toString();
	}
	public void setShape(int index) {
		turtleShape = turtleShapeMap.get(index);
	}
	public void setTurtleShape(Shape newShape) {
		turtleShape = newShape;
	}
	
	
    @Override
    public void updateObservers() {
        for (Observer o : observers){
            o.update(this);
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

	@Override
	public void update(Object o) {
		lineColor = (Color)o;
	}


	public double getShape() {
		// TODO Auto-generated method stub
		for (int n = 0; n < turtleShapeMap.size(); n++) {
			if (turtleShapeMap.get(n).equals(turtleShape)) {
				return (double) n;
			}
		}
		return 0;
	}
	public String turtleShape(){
		return turtleShape.toString();
	}

    public String getWhichString() {
        return whichString;
    }
}

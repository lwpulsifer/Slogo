package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class ClearScreen extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	public ClearScreen() {
		numchildren = 0;
	}
	
	private void clear(Turtle turtle) {
		turtle.clear();
        turtle.setLocation(turtle.getOriginalLocation());
		turtle.clear();
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtleMap);
		//clear(turtleMap);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		double distance = 0;
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
		double CurX = turtleMap.get(n).getLocation().getX();
		double CurY = turtleMap.get(n).getLocation().getY();
		List<SlogoNode> leaf = this.getChildren();
		double xpos = turtleMap.get(n).getOriginalLocation().getX();
		double ypos = turtleMap.get(n).getOriginalLocation().getY();
		distance = Math.pow(Math.pow(xpos - CurX, 2) + Math.pow(ypos - CurY, 2), 0.5);
		clear(turtleMap.get(n));
			}
		}
		return distance;
	}
	
}
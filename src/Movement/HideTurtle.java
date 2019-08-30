package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class HideTurtle extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	
	private void visible(Map<Integer, Turtle> turtleMap) {
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
				turtleMap.get(n).hide();
			}
		}
		//System.out.println("HMMM");
	}
	
	public HideTurtle() {
		numchildren = 0;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
        //System.out.println("HMMM");
		double step = getValue(VarMap, FunctMap, turtleMap);
		visible(turtleMap);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		return 0;
	}
	
}
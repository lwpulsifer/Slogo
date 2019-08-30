package Query;

import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import treenode.SlogoNode;
import turtle.Turtle;

public class Shape extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	
	public Shape() {
		numchildren = 0;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtleMap);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		double index = 0;
			List<SlogoNode> leaf = this.getChildren();
			for (int n : turtleMap.keySet()) {
				if (turtleMap.get(n).isActive()) {
			index = turtleMap.get(n).getShape();
		}} return index;}
	
}
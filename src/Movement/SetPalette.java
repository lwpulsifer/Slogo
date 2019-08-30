package Movement;

import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import treenode.SlogoNode;
import turtle.Turtle;

public class SetPalette extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	
	public SetPalette() {
		numchildren = 4;
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
			index = leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
			double r = leaf.get(1).getExecute(VarMap, FunctMap, turtleMap);
			double g = leaf.get(2).getExecute(VarMap, FunctMap, turtleMap);
			double b = leaf.get(3).getExecute(VarMap, FunctMap, turtleMap);
			for (int n : turtleMap.keySet()) {
				if (turtleMap.get(n).isActive()) {
			turtleMap.get(n).addColor((int) index, Color.rgb((int) r, (int) g, (int) b, 1.0));
		}}
			return index;
			}
	
	
}
package Deprecated;

import java.util.List;
import java.util.Map;
import treenode.SlogoNode;
import turtle.Turtle;

public class VariableNode extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	private String name;
	public void addName(String Name) {
		name = Name;
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
		if (VarMap.containsKey(this.name) && this.numchildren==0) {
			return VarMap.get(this.name); 
		}
		List<SlogoNode> leaf = this.getChildren();
		double x = leaf.get(0).getValue(VarMap, FunctMap, turtleMap);
		return x;
		}
	@Override
	public String getName() {
		return name;
	}

}

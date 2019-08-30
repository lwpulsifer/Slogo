package treenode;

import java.util.Map;

import turtle.Turtle;

public class StringNode extends SlogoNode {

	private String word;

	
	//return the name assigned to the variable node
	public String getName() {
		return this.word;
	}
	
	public StringNode(String word) {
		this.word = word;
	}
	
	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		return 0;
	}

}

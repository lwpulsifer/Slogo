package treenode;

import turtle.Turtle;

import java.util.Map;

import turtle.Turtle;

//interface for setting the structure of a Command Node
public interface Command {
	
	/*
	 * getExecute() performs the function of the Node, encapsulates function within each Node
	 * returns a double file for prior nodes to access its value.
	 */
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap);
	
	/*
	 * returns a certain value of the node
	 */
	// public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> funct);

	public double getDummyExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle);
}

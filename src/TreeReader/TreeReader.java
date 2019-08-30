package TreeReader;

import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class TreeReader {
	public double evaluate(SlogoNode node, Map<String, Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap) {
		return node.getExecute(VarMap, funct, turtleMap);
	}
	public TreeReader() {};
}

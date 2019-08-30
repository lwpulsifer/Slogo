package Query;

import treenode.SlogoNode;
import turtle.Turtle;

import java.util.Map;

public class BracketNode extends SlogoNode {

	public BracketNode() {
		numchildren = 0;
	}

    @Override
    public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap) {
        return 0;
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
        return 0;
    }
}

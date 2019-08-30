package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Repeat extends SlogoNode{

    public Repeat(){
        this.setNumChildren(2);
    }
    
    @Override
    public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
    		int counter;
    		List<SlogoNode> leaf = this.getChildren();
        double ret = 0;
        counter = (int) leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
        for (int i = 0; i < counter; i ++) {
        	ret = leaf.get(1).getExecute(VarMap, FunctMap, turtleMap);
        }
        return ret;
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
        return this.getValue(VarMap, FunctMap, turtleMap);
    }
}

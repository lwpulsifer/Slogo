package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class IfElse extends SlogoNode{

    public IfElse(){
        this.setNumChildren(3);
    }
    
    @Override
    public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
        List<SlogoNode> leaf = this.getChildren();
        double ret = 0;
        double boolin = leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
        if (boolin != 0){
        	ret = leaf.get(1).getExecute(VarMap, FunctMap, turtleMap);
        }
        else
        	ret = leaf.get(2).getExecute(VarMap, FunctMap, turtleMap);
        return ret;
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
        return this.getValue(VarMap, FunctMap, turtleMap);
    }
}

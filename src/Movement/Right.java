package Movement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Right extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	public Right(){
		numchildren = 1;
	}
	private void right(Map<Integer, Turtle> turtleMap, Map<Integer, Double> map) {
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
				turtleMap.get(n).setHeading(turtleMap.get(n).getHeading() - map.get(n)); //TODO: Update according to Jamie's stuff
			}
		}
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double step = 0;
		HashMap<Integer, Double> map = new HashMap<>();
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
				VarMap.put("ID_RESERVED", (double) n);
				step = getValue(VarMap, FunctMap, turtleMap);
				map.put(n, step);
			}
		}
//        for (Integer i : map.keySet()){
//            System.out.println("Key: " + i + " Value: " + map.get(i));
//        }
		right(turtleMap, map);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		return leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
	}
	
}

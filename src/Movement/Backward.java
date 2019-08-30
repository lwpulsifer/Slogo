package Movement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import treenode.SlogoNode;
import turtle.Turtle;

public class Backward extends SlogoNode{
	
//	private double value = 0;
//	private double distance = 0;
	public Backward() {
		numchildren = 1;
	}

	private void backward(Map<Integer, Turtle> turtleMap, Map<Integer, Double> map) {
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
				Point2D point = new Point2D(turtleMap.get(n).getLocation().getX() + map.get(n) * Math.sin(Math.toRadians(turtleMap.get(n).getHeading())),
				turtleMap.get(n).getLocation().getY() + map.get(n) * Math.cos(Math.toRadians((turtleMap.get(n).getHeading()))));
				turtleMap.get(n).setLocation(point);
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
		backward(turtleMap, map);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		return leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
	}
	
}

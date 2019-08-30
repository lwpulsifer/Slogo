package Movement;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class SetHeading extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	public SetHeading(){
		numchildren = 1;
	}
	private void setHead(Map<Integer, Turtle> turtleMap, Map<Integer, Double> map) {
        for (int i : turtleMap.keySet()){
            turtleMap.get(i).setHeading(map.get(i));
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
        setHead(turtleMap, map);
        return step;
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double ret = 0;
		List<SlogoNode> leaf = this.getChildren();
		double buffer = leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
		if ((buffer % 360)== 0) {buffer = 360.0;}
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
		        double buffer1 = turtleMap.get(n).getHeading();
		        ret = Math.abs(Math.min(buffer1 - buffer, buffer - buffer1));
			}
		}
		return ret;
	}
	
}

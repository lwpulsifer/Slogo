package Movement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;
import views.SlogoView;

public class SetTowards extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	public SetTowards(){
		numchildren = 2;
	}
	private double xpos = 0;
	private double ypos = 0;
	private void setHead(Map<Integer, Turtle> turtleMap, Map<Integer, Double> map) {
        for (Integer i : turtleMap.keySet()){
            if (turtleMap.get(i).isActive()){
                turtleMap.get(i).setHeading(map.get(i));
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
                step = -1 * Math.toDegrees(Math.atan((xpos - turtleMap.get(n).getLocation().getX())/
                        (ypos - turtleMap.get(n).getLocation().getY())));
                map.put(n, step);
            }
        }
//        for (Integer i : map.keySet()){
//            System.out.println("Key: " + i + " Value: " + map.get(i));
//        }
        setHead(turtleMap, map);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double newhead = 0;
		List<SlogoNode> leaf = this.getChildren();
		xpos = leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
		ypos = leaf.get(1).getExecute(VarMap, FunctMap, turtleMap);
		//hi here are some magic numbers please dont judge will be fixed later
		xpos = xpos + 489.282857;
		ypos = ypos + 251;

		//double initial = turtleMap.get(n).getHeading(); //TODO: Update according to Jamie's stuff

		newhead = -1 * Math.toDegrees(Math.atan((xpos)/(ypos)));

		
	    return newhead;}
	
}
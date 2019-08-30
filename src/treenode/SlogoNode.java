package treenode;

import turtle.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * builds the abstract node class that all commands will follow
 */
public abstract class SlogoNode implements Command {
	protected int numchildren = 0;
	private List<SlogoNode> children = new ArrayList<>();
	
	/*
	 * adds a SlogoNode to the children nodelist
	 */
	public void addChild(SlogoNode n) {
		this.children.add(n);
	}
	public abstract double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap);
	
	//return the name of the variable assigned to the node
	public String getVariableName() {
			return this.getName();
		}
	
	public int getNumchildren(){
		return numchildren;
	}
	/*
	 * returns children
	 */
	public void setNumChildren(int n) {
		numchildren = n;
	}
	public List<SlogoNode> getChildren(){
		return children;
	}
	public double getDummyExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
//		return getExecute(VarMap, FunctMap, turtle);
		return 0;
	}
	
//	//returns the name of the String
//	public void setName(String input) {
//		name = input;
//	}
	
	public String getName() {
		return "does not exist";
	}
	
	/*
	 * check if the node is a leaf of the tree
	 */
	public boolean isLeaf() {
		if(this.children.size()==0){
			return true;}
		else {
			return false;
		}
	}
}
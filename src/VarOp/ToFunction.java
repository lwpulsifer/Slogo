package VarOp;

import java.util.List;
import java.util.Map;

import Deprecated.VariableNode;
import treenode.SlogoNode;
import turtle.Turtle;

/*
 * after a function is called
 */
public class ToFunction extends SlogoNode {
	private SlogoNode head;
	private String name;
	
	public ToFunction(SlogoNode headOfCommands, String name) {
		this.head = headOfCommands;
		this.name = name;
		numchildren = 1; //only one child with all of the variables
	}
	//changes the value of the variable
	public void changeValue(Map<String, Double> VarMap, String variableName, double val) {
			VarMap.put(variableName, val);
	}
	
	public String getToName() {
		return this.name;
	}
	

	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		if (this.getChildren().size()==0) {
			System.out.println("reached");
			System.out.println(this.head);
			this.head.getExecute(VarMap, FunctMap, turtleMap);
			return this.head.getValue(VarMap, FunctMap, turtleMap);
		}
		int numOfParameters = this.getChildren().get(0).getNumchildren();
		double[] parameterArray = new double[numOfParameters];
		for (int i = 0; i<numOfParameters; i++) {
			parameterArray[i]= this.getChildren().get(0).getChildren().get(i).getExecute(VarMap, FunctMap, turtleMap);
		}
		//parameterArray now has all the parameters for the commands
		//head is the head of the command master node.
		int numOfVariableNodes = 0;
		for (int i = 0; i<this.head.getNumchildren(); i++) {
			SlogoNode current = this.head.getChildren().get(i);
			if (current instanceof VariableNode) {
				numOfVariableNodes++;				
			}
		}
		//check if the parameterArray does not have the right number of arguments
		if (parameterArray.length!=numOfVariableNodes) {
			System.out.println("WRONG NUMBER OF ARGUMENTS");
			throw new IllegalArgumentException();
			
		}	
		//run through each node and convert each variable node to return a value. 
		int count = 0;
		for (int i = 0; i<this.head.getNumchildren(); i++) {
			//SlogoNode current = this.head.getChildren().get(i);
			if (this.head.getChildren().get(i) instanceof VariableNode) {
				changeValue(VarMap, this.head.getChildren().get(i).getVariableName(), parameterArray[count]);
				count++;
			}
		}
		return this.head.getExecute(VarMap, FunctMap, turtleMap);
		
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		return this.head.getExecute(VarMap, funct, turtleMap); //returns the value of the head
	}

}

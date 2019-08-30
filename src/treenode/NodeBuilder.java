package treenode;

import views.Observer;
import views.SceneElements.Observable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import VarOp.ToFunction;

public class NodeBuilder{

	private Map<String, SlogoNode> functionMap;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private static final String LANGUAGE_FILE = "English";
	private static final String NUMBERNODE_ADDRESS = "treenode.NumberNode";
	private static final String VARIABLENODE_ADDRESS = "treenode.VariableNode";
	private static String language = LANGUAGE_FILE;
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+language);
	private static Map<String, String> languageMap = createLanguageMap(myResources);
	
	public NodeBuilder(Map<String, SlogoNode> functions) {
		this.functionMap = functions;
	}
	/*
	 * iterates through each value and returns a map of all the languages.
	 */
	private static Map<String, String> createLanguageMap(ResourceBundle myResources){
		Map<String, String> languageMap = new HashMap<>();
		Set<String> keySet = myResources.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String curr = it.next();
			//put in the resource string as the key, and the key as the value
			if (isMultipleKeyword(myResources.getString(curr))) {
				//System.out.println(myResources.getString(curr));
				String[] splitWords = myResources.getString(curr).split("[|]");
				//System.out.println(splitWords.length);
				for (int i = 0; i<splitWords.length;i++) {
					languageMap.put(splitWords[i],  curr);
				}	
			}else {
				//System.out.println(myResources.getString(curr));
				languageMap.put(myResources.getString(curr), curr);
			}
		}
		return languageMap; //returns a map with all of the 
	}
	/*
	 * checks if there is an Or operator that splits the words
	 */
	private static Boolean isMultipleKeyword(String input) {
		return input.contains("|");
	}
	
	public static void changeLanguage(String newlanguage){
	    language = newlanguage;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+language);
        languageMap = createLanguageMap(myResources);
    }
	/*
	 * creates Variable Node
	 */
	public SlogoNode createVariableNode(String input) {
		Class<?> commandObject = null;
		try { //try to create a new class object based on name.
			commandObject = Class.forName(VARIABLENODE_ADDRESS);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor<?> c = commandObject.getConstructors()[0];
		SlogoNode command = null;
		try {
			command = (SlogoNode) c.newInstance(input);
		}
		catch (InstantiationException | IllegalAccessException| IllegalArgumentException |InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;
	}
	
	/*
	 * creates Number Node
	 */
	public SlogoNode createNumberNode(String input) {
		Class<?> commandObject = null;
		try { //try to create a new class object based on name.
			commandObject = Class.forName(NUMBERNODE_ADDRESS);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor<?> c = commandObject.getConstructors()[0];
		SlogoNode command = null;
		try {
			command = (SlogoNode) c.newInstance(Double.parseDouble(input));
		}
		catch (InstantiationException | IllegalAccessException| IllegalArgumentException |InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;
	}

	public SlogoNode createStringNode(String input) {
		SlogoNode stringNode = new StringNode(input);
		return stringNode;
	}
	
	public Boolean checkFunctionMap(String input) {
		return functionMap.containsKey(input);
	}
	
	public Boolean checkLanguageMap(String input) {
		return languageMap.containsKey(input);
	}
	
	public SlogoNode createToFunctionNode(String input) {
			SlogoNode commandHead = functionMap.get(input);
			SlogoNode toFunction = new ToFunction(commandHead, input);
			System.out.println("TO function created");
			return toFunction;
	}
	/*
	 * builds an individual Command Node
	 */
	public SlogoNode createNode(String input) {
		String formalCommandName = null;

		if (languageMap.containsKey(input)) { //if the map exists
			formalCommandName = languageMap.get(input);
		}
		else{
			throw new InvalidParameterException("NOT A COMMAND");
		}
		System.out.println(functionMap.keySet());
		//if statement if the command is a predefined TO function.

		/*
		 * create a method that checks if the function exists and returns the correct command object with parameters.
		 */
		Class<?> commandObject = null;
		try { //try to create a new class object based on name.
			commandObject = Class.forName("Movement."+formalCommandName);
			}
		catch (ClassNotFoundException e1) {
			try { //try to create a new class object based on name.
				commandObject = Class.forName("Bools."+formalCommandName);
			} catch (ClassNotFoundException e2) {
				try { //try to create a new class object based on name.
					commandObject = Class.forName("MathOps."+formalCommandName);
				} catch (ClassNotFoundException e3) {
					try { //try to create a new class object based on name.
						commandObject = Class.forName("Query."+formalCommandName);
					}catch (ClassNotFoundException e4) {
						try {
							commandObject = Class.forName("VarOp." + formalCommandName);
						}catch (ClassNotFoundException e5) {
						e4.printStackTrace();
					}
				}
			}

			}
		}
		Constructor<?> c = commandObject.getConstructors()[0];
		SlogoNode command = null;
		try {
			command = (SlogoNode) c.newInstance();
		}
		catch (InstantiationException | IllegalAccessException| IllegalArgumentException |InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;
	}

}

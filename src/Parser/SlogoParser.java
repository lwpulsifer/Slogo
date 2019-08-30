package Parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SlogoParser {
	
	private final String ERROR = "NO MATCH";
	
	private List<Entry<String, Pattern>> mySymbols;
	
	/*
	 * create an empty parser
	 */
	public SlogoParser() {
		mySymbols = new ArrayList<>();
	}
	
	/*
	 * Adds the given resource file to this language's recognized types
	 * To be used to add regex language resource files to the program parser.
	 */
	public void addPatterns (String syntax) {
		ResourceBundle resources = ResourceBundle.getBundle(syntax);
		Enumeration<String> iter = resources.getKeys();
		//iterates through all elements in the resource file and compiles them with regex
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}
	
	/*
	 * Returns language's type associated with the given text if one exists
	 * 
	 */
	public String getSymbol (String text) {
		for (Entry<String, Pattern> e : mySymbols) {
			if (match(text, e.getValue())) {
				return e.getKey(); //checks if a pattern matches, and return the right key
			}
		}
		return ERROR;
	}
	
	/*
	 * returns true if the given text matches the given regular expression pattern
	 */
	 private boolean match (String text, Pattern regex) {
	        // THIS IS THE KEY LINE
	        return regex.matcher(text).matches();
	    }

}

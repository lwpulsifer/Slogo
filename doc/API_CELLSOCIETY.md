## API Critique
Jamie Kim, Brandon Guo, Liam Pulsifer, Ryan Fu


****
### Simulation
#### Internal

```java 
public abstract class Simulation	{ 
  	public static final List<String> SUPPORTED_TYPES = Arrays.asList(new String\[\] 
	public Simulation(Grid g, Neighborhood nType)	
	public Grid update()	
	public void setNeighborhood(Neighborhood newN)	
	public Map<String, Color> getStates()	
	public Map<String, Integer> getStatesNumbers()	
	public Grid getGrid()	
}
```
```java 
public class SimulationConway extends Simulation	{ 
  	public SimulationConway(Grid g, Neighborhood nType)	
}
 ```
 ```java
public class SimulationFire extends Simulation	{ 
  	public SimulationFire(Grid g, Neighborhood nType, double prob)	
}
 ```
 ```java
public class SimulationRPS extends Simulation	{ 
  	public SimulationRPS(Grid g, Neighborhood nType)	
	public Grid update()	
}
 ```
 ```java
public class SimulationSegregation extends Simulation	{ 
  	public SimulationSegregation(Grid g, Neighborhood nType, double satisfied)	
	public Grid update()			
}
 ```
 ```java
public class SimulationSugar extends Simulation	{ 
  	public SimulationSugar(Grid g, Neighborhood nType, int numAgents)	
	public Grid update()	
	public int distance(Cell c, Cell other)	
}
 ```
 ```java
public class SimulationWaTor extends Simulation	{ 
  	public SimulationWaTor(Grid g, Neighborhood nType, int prey\_breed\_age, int pred\_breed\_age, int pred\_energy, int pred\_regain_energy)	
	public Grid update()	
}
```
The methods in these classes are only used within its backend module. The abstract class, Simulation, lays the foundation for building future simulations and should be a part of the internal API. It does not rely on other classes, and future coders within the module can extend Simulation to build future simulations.

#### External
The CustomNeighborHood class is an example of an external API because it allows members on the team to access information about the Cell to be utilized when building the simulation. Methods such as getNeighbors and addToNeighbors work with both the Cell class and Grid class to implement each simulation.
```
public class CustomNeighborhood implements Neighborhood {	 
  	public List<Cell> getNeighbors(Grid g, Cell c)  
	public List<Cell> getNeighbors(Grid g, Cell c, String specs)    
	public List<Integer\[\]> getCorners(int x, int y, int number)  
	public List<Integer\[\]> getEdges(int x, int y, int number)  
	public List<Integer\[\]> getKnight(int x, int y, int number)  
	public List<Integer\[\]> getAdjacent(int x, int y, int number)  
	public void addToNeighbs(Grid g, List<Cell> neighbors, List<Integer\[\]> poss)	
}
```

****
### Visualization

#### Internal
```java 
public class Main extends Application { 
  	public void start (Stage primaryStage) throws Exception 
}
```
The start method is part of the internal API because it helps future coders within the visualization module extend it.

``` java 
public class UIHelper { 
  }
 
public class UserInterface extends Main { 
  	public void setupScene() 
}
```
These classes and their methods are part of the interal API because they only work within one module and don't help anyone working outside of the module.

#### External
 ```java 
public class Viewer extends UserInterface { 
  	public void paintScene(Stage stage) 
}
```
```java 
public class CellRPS extends Cell	{ 
  	public CellRPS(Color color, int x, int y)	
	public CellRPS(Color color, int x, int y, int lv)	
	public void advLevel()	
	public void regLevel()	
	public boolean getAlive()	
	public int getLevel()	
}

```
The Cell and its subclasses have methods which are external because they need to be accessed both by the Simulation and the Visualization modules. 
****
### Configuration 
#### Internal
```java
public abstract class XMLCellsBuilder { 
  	public abstract List<Cell> getCells(Element root, HashMap<String, Color> states, int\[\] dims);
	public abstract List<Cell> getWaTorCells(Element root, HashMap<String, Color> states, int\[\] dims);
	public List<Cell> getSugarCells(Element root, HashMap<String, Color> states, int\[\] dims)
	public List<Cell> getRPSCells(Element root, HashMap<String, Color> states, int\[\] dims) 
}

public abstract class XMLHelper { 
  	public List<Cell> getCells(Element root, String methodName) 
	public abstract Map<String, Color> getStates();
	public abstract Grid getGrid(NodeList dims, List<Cell> cells);
	public abstract Simulation initSimulation(NodeList params, Grid g, Neighborhood n);
}

```

These classes exist in order to provide extension functionality so that new subclasses can be created. They are only used within the configuration module and are never instantiated or called elsewhere. 


#### External 
``` public class WaTorXMLHelper extends XMLHelper { 
  	public Grid getGrid(NodeList dims, List<Cell> cells) 
	public Map<String, Color> getStates() 
	public Simulation initSimulation(NodeList params, Grid g, Neighborhood n) 
}

public class XMLManualCellsBuilder extends XMLCellsBuilder { 
  	public List<Cell> getCells(Element root, HashMap<String, Color> states, int\[\] dims)
	public List<Cell> getWaTorCells(Element root, HashMap<String, Color> states, int\[\] dims) 
	public List<Cell> getSugarCells(Element root, HashMap<String, Color> states, int\[\] dims) 
}
 
public class XMLProceduralCellsBuilder extends XMLCellsBuilder{ 
  	public List<Cell> getCells(Element root, HashMap<String, Color> states, int\[\] dims) 
	public List<Cell> getWaTorCells(Element root, HashMap<String, Color> states, int\[\] dims) 
}
 
public class XMLRandomCellsBuilder extends XMLCellsBuilder{ 
  	public List<Cell> getCells(Element root, HashMap<String, Color> states, int\[\] dims) 
	public List<Cell> getWaTorCells(Element root, HashMap<String, Color> states, int\[\] dims) 
}

```
These classes and the methods within are external API elements because they are called by outside methods to get Lists of cells. They have to return those lists and get input from outside methods in order to do so. This makes them external because they no longer work solely within the Configuration module, but instead communicate with the Simulation module.
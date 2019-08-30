### Created by Ryan Fu  (partner: Martin Muenster)

#### Part 1
1. We discussed to create a flexible design that utilizes a superclass to builds the basic structure of a command. This superclass is then used by subclasses that inherit the properties of this superclass which specify the command's functionality. We intend to use either an abstract superclass or interface to create the skeleton for the back-end command structure.
2. Each command class will work by having a public method that allows the command to be executed; however, the details of how the command functionalities will be hidden. This will make sure that the private values and implementation decisions that define each command are encapsulated within each class. 
3. Some exceptions that might occur may be parameter values that cause the commands to malfunction. This could take the form of out of bound values (bounds exceptions). Invalid turtle object that is passed through the parameter for the commands. The object must have turtle properties in order for the command to run. 
4. I think my API/design is good because it is flexible enough for additional command methods to be added by creating a new class. It is also simple and understandable because all of the command functionalities are separated by classes. This provides a clear separation between the back-end command design, and the front end (turtle) interactions.

#### Part 2:
1. The design pattern that we will most likely be using is the factory method. The factory method is represented by the commandBuilder class, which will be used to create and instantiate new command objects that interact with the turtle. We might also consider using the command pattern to encapsulate a command request in the IDE. This will allow various parameterization of each command based on the user input as well as saving the requests to a queue if multiple command lines are written. 

2. I am most excited to work on building the commandBuilder in order to take in string values from the interpreter to instantiate the command objects. I believe that this design pattern will be tricky, but crucial to implement in this project by allowing classes to be built based on the user input. I am excited because this will be a good challenge.

3. I am worried about the functionality for each individual command class. It will be difficult to accurately manipulate the turtle object while maintaining the encapsulation of the specific command class. This will be the bridge between the back-end and front-end and needs to be clean in its design. 

4. 	
	* "fd 50" is called
	* "fd 50" and "bk 50" are called consecutively 
	* "fd 5000" is called and should trigger and out of bounds error
	* implementing a new command such as "zigzag" by creating a new class.
	* nested command methods are called that require the preservation of order and parameter values.
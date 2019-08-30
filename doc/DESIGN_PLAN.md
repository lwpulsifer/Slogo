# SLOGO Design Plan
##### Authors: Jamie Kim, Liam Pulsifer, Ryan Fu, Brandon Guo

## Introduction
This project addresses the creation of Integrated Development Environments, using the simplified coding language Logo. In this project, we will be creating a user-friendly coding environment supporting a simple coding language that is used by beginners. The user can interact with a simulation, in which there is a display of a turtle. Commands will control the turtle with different movements such as going forward and changing directions, and etc. The primary goal is to be able to have a flexible program that responds to a variety of commands. Thus, this IDE should focus on helping users to experiment with commands, build up complex expressions with previously entered commands and keeping the visual representation appropriately updated. 

The closed elements of the design will be the abstract classes such as the command and the simulation because adding a new instruction or a new moving object does not require changes in these classes. Instead, these superclasses will be extended to further implement new functionalities.


## Design Overview

Our program's design will consist of four main APIs, each of which contains at the moment one major class.
1. Front-end Internal API: The class SlogoView hosts the main window of the application, containing the command entry field, the turtle-viewing field, and the variable- and command-history views. The SlogoView will be responsible for getting text input from the user and passing it to the backend (Interpreter class), maintaining a history of user instructions (probably within a CommandHistory class), and rendering the turtle and its path in a viewing window. 
2. Front-end External API: The Turtle class will be responsible for holding its own current and previous positions, an image to pass to SlogoView, and whether its pen is currently drawing or not. Its public methods will include ones allowing its position, pen-status, and image to be changed. 
3. Back-End Internal API: The class Interpreter will build a series of instructions based on the user's input into the command line and, by calling methods inside the Controls class, building a series of instructions that represents the user's input. It then executes that series upon whatever object it is passed. This design allows for more flexibie coding (the user can define their own functions or movement patterns) and more importantly the ability to act on multiple different objects or images, just by passing in a different object. Then, because the Interpreter executes by changing the positions of the object it is passed, no return value is necessary. 
4. Back-End External API: The Class Controls will contain methods which update the position of the Turtle and return the line to be drawn between the turtle's old and new positions if necessary. For example, a method forward(Turtle t, 50) might move the given Turtle 50 pixels forward in the direction of its orientation. 

Design Overview Picture:
![](./doc/https://coursework.cs.duke.edu/CompSci308_2018Spring/slogo_team10/blob/master/doc/28169342_10211288155383999_95604558_o%20(1).jpg)
## User Interface
The user interface will have four sections. First, the tool bar will have the control buttons for settings. The workspace will keep track of previous inputs and different variables that are currently being used. The graphic display is where the turtle and the lines it draws are shown. The last component is the console where commands will be entered. 
![User Interface Design Drawing](doc/SlogoUI.jpg "UI Design")

## API Details
* Front-End Internal API: The front-end internal API will mostly consist of the SlogoView class, which will create the basic Scene elements making up the main window of Slogo. It will instantiate a TextField object which will be used as the console. This will allow the user to enter commands to the turtle interactively. The user will be able to see the results of those commands visually by first passing the string containing the commands to the Interpreter, then getting the results later from the Command class. In order to view the command history and current variables defined and their values, it will also be able to access a Variables and a History object or data structure (we are undecided as yet which we will use), which will hold those data and allow for their access. This API, however, will be minimal because since we're not using any form of Controller, most of the methods involved will be private. 

```java 
public interface SlogoView(){

/* Starts the animation and initializes scene *objects
*/
public void start(Stage stage )
}


```

* Front-End External API: The front-end external API will consist of the Turtle class, which holds the information like the location, direction, and state of the turtle. Main resources used will be JavaFx nodes to create UI components and Event Handlers to interact with the back end. This API is meant to be used to store information about the current and the previous state of the turtle which is held between the front and back end, and display the updated location of the turtle on the user interface. It can be extended by creating new subclasses of the Turtle class that could take in a different image. The class within this API is the Turtle class which allows for communication simulation-related information stored to be used for display. 
```java     
public interface Turtle() {

    public void setLoc (int x, int y);
    public void setOrigin (int origin);
    public void switchPen();
}
```

* Back-End Internal API: The back-end internal API is essentially the Interpreter. The Interpreter will take in a string representing an instruction or a series of instructions from the command line and be in charge of interpreting (passing it into a method called parseInput(String str)) that series of instructions by calling methods from the Controls Class and combining those controls. Those controls will control the turtle or whatever object it is passed. To handle variable definitions and operations, another Variable_Controls class will be defined that handles all the mathematical manipulations and variable definitions. This way, the changes can all happen at a lower level and the Interpreter does not need to do any manipulations on it's own, allowing for better encapsulation. It then passes in an object that the movements are to be performed on and applies the series of changes to the object and returns that object to LogoView to be drawn. 
    ```java
    public interface Interpreter(){
            public double parseInstruction(String instruct, Object turtle);
    }
    ```
* Back-End External API: 
    Command Class API holds all of the specific movements and commands that can be performed on the turtle or any arbitrary object in the scene. It requires a movable object as an parameter and is intended to be called by a view class to allow modifications to the view for this object in the scene. For future, additional requirements, additional methods or classes that inherit for a basic command superclass can be added for more functionality to modify the position of an object. The reason for creating this class to separate the algorithm and back-end design that changes the position of the turtle from the turtle class. We wanted to ensure that the turtle class does not have too many responsibilities. 
    ```java
    public interface commandController(){
            public void moveForward(MovableObject turtle, int distance);
            public void Rotate(MovableObject turtle, int degrees);
            public void dropPen(MovableObject turtle);
            public void pickUpPen(MovableObject turtle);
    }
    ```
    


## API Example Code
 * User types 'fd 50' as the command in the command window text field and presses run
 * Interpreter.run("fd 50") parses through the word to determine if the command is valid.
 * Interpreter runs Interpreter.split() which parses the word to retrieve the command. Interpreter then runs Interpreter.isValid() to determine if the command exists. 
 * If command is valid, Interpreter will call Interpreter.sendToCommand(), which calls the appropriate method in the command class, in this case Command.forward(turtle, 50). This method takes in the Turtle and moves its position 50 pixels forward in the direction of its orientation. 
 * Finally, Command will return the appropriate Integer to main to be printed in the console, in this case 50 

##### Jamie's Use Cases: 
1. User enters "Right 90", and the turtle moves 90 degrees clockwise.
    * In Command Class, the line entered will instantiate a Right object (Command command = new Right(90)). And, in the Turtle class, Turtle turtle = new Turtle will be instantiated, and all the appropriate information about the state of the turtle will be retreived. Then, command.execute() will be called, in which turtle.setRotate(turtle.get() + 90) turns the turtle clockwise.
 
2. The user enters "REPEAT 10 FORWARD 5", and the turtle moves 50 steps forward.
    * This line will instantiate a Repeat object by Command command = new Repeat(iterations). The turtle class will get the appropriate data and the command.execute() will create a for loop iterating over iteration number of times. Each iteration, the loop will instantiate a new local Forward command. 
 
##### Ryan's Use Cases:
1. Method called from intepreter class. 
    * After the interpreter determines if the input is a valid command, it will call the correct command from the command Class. For example, if the user correctly types in moveForward 20, intepreter will call command.moveForward(turtle, 20) which takes in the parameter for a movable object and the value for movement. 
2. Command modifies the movable object. 
    * Since the command takes in a movable object (such as the turtle) as an parameter, the command can change the position of the turtle object by calling turtle.setPosition() or turtle.setOrientation. The algorithm of how the turtle moves will be calculated in each command method/class, but the class will ultimately rely on the turtle class to set its own position.

##### Brandon's Use Cases:
1. Manipulating an object
    * The interpreter will be passed a string representing the instruction (by way of interpreter.parseInstruction(String Instruct, Object object)) and break the instruction up into manageable pieces. It then will make sucessive calls of Interpreter.sendToCommand(), which will call the related method inside the Controls class. Using these methods, it will build a series of operations and perform said operations on the object passed into ParseInstruction(). These instructions will change the state of the object, and return a number with no current physical representation. 
2. Manipulating a variable
     * Similar to the way an object is manipulated, the Interpreter class will instantiate a map of strings to numberical values, likely doubles, and store any variable declarations inside that structure. What to add or define will be passed to Interpreter.parseInstruction(String Instruct, Object object), which will call the related method inside the Variables_Controls. It will determine what variable to act on within the string and perform the retrieved methods on that variable. These instructions will change that variables value in the map, and return a physical number with no current physical representation. 

##### Liam's Uses Cases:
1. Passing text input to the Interpreter
    * The user will input text into the text field and then press enter. This will call an event handler which will call the method handleTextInput(), which will change the given input string to a completely lower-case string and then call interpreter.parseInstruction(String s, Turtle t), passing the lower-case string and the Turtle object on the screen. 
2. Changing the background color
    * The user will select a background color from a drop-down menu, which will trigger an event handler to call the handleColorInput() method. This method will reinitialize the turtle-view field of the Scene, but pass that method a different Color variable, and then re-add it to the root, resulting in a changed background color. 


## Design Considerations
1. The first design consideration is to have the turtle class function as an object that houses all of the methods that allow it to move. This would most likely require an interface that implements the various movement methods which will be found and specifed in the turtle class. The benefit of this design is that the turtle object can function as its own object that is aware of its movement capabilities. The downside is that all of the algorithmic methods that control the turtles movement will exist within the single turtle class. Other modules must call a public method directly from the turtle class in order to perform a command which may interfere with the encapsulation of the turtle class. This design assumes that any additional objects must implement the command interface to obtain all of the command methods. 

2. The second design consideration is to create a commandController super class that takes in two parameters, a movable object (turtle), and a second parameter. Future methods such as rotate and moveForward can inherit the structure of commandController and take in these two parameters that control the movement and change in position of the object. The benefit of this design is that all of the backend movement algorithms are related by a single class/package and allows the commandController to function as an API that modifies an arbitrary movable object. This separates the logic of the turtle's movement from the turtle class and allows the turtle  class' encapsulation to only contain the turtle's property and function as a movable object in the scene.
 
## Team Responsibilities
Currently, the division of labor places Jamie and Liam on the frontend and Brandon and Ryan on the backend. These subgroups will be working together and assigning tasks accordingly, and will meet periodically to incorporate front and back end changes. The class responsibilities will be as follows:

* Jamie : Turtle
* Liam : SlogoViewer + Interpreter assistance
* Ryan : Command Interface/Inheritance Class
* Brandon : Interpreter
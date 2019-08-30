# API\_REVIEWb\_lp139_bmc41.md

## Liam's API

### Part 1
##### External Front-End - Overview
* Classes
    * SLogoView - main class that displays GUI, launches program. Uses 4 main classes to run simulation. Each class takes in information from backend in order to function. Elements are updated when a command is called via the back end (e.g. Commands related to TurtleView will pass in TurtleView object and act on its fields).
        1. History - Displays most recent commands entered. Will either display only valid past commands or will mark invalid ones so user knows they were invalid
        2. TurtleView - Displays turtle's position on screen and its path that was written by the pen. Also displays background on which Turtle moves.
        3. Console - Command input field. Passes inputted text to backend classes to handle data processing
        4. VariableView - Displays values of variables created by the user

Liam's Responsibilities

* All 4 classes used in SLogoView

Flexibility
* Designed so that the user can implement changes by adding code in the backend without needing to change the front end
    * Modular - UI is divided into specific, well-defined regions on the screen. Can choose to display or not to display simply by instantiating different objects in that location.

* Add commands by creating new command classes on the back end

Encapsulation
* All front end classes have limited access to back end. Passes data entered by user to back end, back end handles data processing
* All classes only responsible for knowing their own data; don't need to access other classes data or back end data.
    * E.g. TurtleView only needs to know its own position, color of background, pen up/pen down, etc.

Exceptions or Error Cases
* Logical errors - e.g. Turtle moving offscreen
    * Display warning and reset simulation to most recent state before erroneous command was called
* Syntax error - catch exception from backend and display corresponding error message

Why is it good?
* Flexible
* All pieces have well-defined roles without having access to other classes
* No component does too much - roles are small and well-defined

### Part 2:

How are Design Patterns represented/how can they be used?
* Factory pattern will be implemented for instantiation of new commands
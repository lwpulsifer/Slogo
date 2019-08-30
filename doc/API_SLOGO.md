# SLogo Architecture Design
Jamie Kim, Brandon Guo, Liam Pulsifer, Ryan Fu

****
1.  When does parsing need to take place and what does it need to start properly?
    * Parsing needs to happen when a user types in a command and presses "ENTER". To start properly, it should need a valid command. In order to be both flexible and dependable, error checking will need to be a part of the parsing, though error messages could simply be "command not recognized, please try again".
2.  What is the result of parsing and who receives it?
    * There will need to be a back end class like an Interpreter which can take in the parsed output (perhaps a list of numbers or another data structure). 
3.  When are errors detected and how are they reported?
    * Errors are detected when the run button is pressed, so that an exception will be caught if an error occurs. 
    * In the event of an error, a command prompt will be displayed showing what type of error was caught.
    * Some possible errors could be:
        * Syntax errors in the slogo code
            * Unrecognized input or commands
            * wrong characters
        * Logic error
        * Null pointers
        * Out of bounds exceptions
        * Unrecognized input
4.  What do commands know, when do they know it, and how do they get it?
    * What to know?
        * Given an input (command), should be able to return the correct data to the interpreter
    * When do they know it?
        * When the user presses enter 
    * How do they get it?
        * Objects are passed in through parameters (user/text input in the command line)
5.  How is the GUI updated after a command has completed execution?
    * The interpreter will decide the new positions of the visualization elements and then send them to a Visulizer class. 

# CRC Cards


# Create APIs

* External: between the view (front end) and model (back end) modules
    * How you plan to separate the graphical interface from the interpreter and how you plan to let them communicate when necessary.
        - The LogoView will obtain a String from the user interface. It will pass the input to the interpreter, which will parse through the text to acquire a valid command. This command is then sent to the Commands interface, which updates a new Turtle object. This turtle object will then be redisplayed on the GUI using the LogoView.
    *  What objects will be used for communication: making it clear how needed information will get where it is needed, what will be available and what will be encapsulated, what things will be immutable, and what errors may be thrown.
        

* Internal: within each module (i.e., for its future programmers/maintainers)
    -   How you plan to provide paths for extension through interfaces, inheritance, and design patterns for new features you might reasonably expect to be added to the program.
    -   What subclasses or implementing classes will be used to extend your part to add new features: making it clear what kind of code someone will be expected to write, what parts of your code you expect to be closed to modification, and what errors may be thrown.
    -   Note, while some of these methods may be `public`, many may be `protected` or package friendly.
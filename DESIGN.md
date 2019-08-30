# SLogo Design Discussion

### High-Level Design Goals
- Provide a flexible development environment in which users can code basic commands for any number of virtual "turtles" on a display screen . 
- Allow for a variety of commands, including
    - Movement commands
    - Variable definition and operation
    - Basic Arithmetic
    - Function Definition
    - Environmental editing
- Allow for a variety of front-end display elements displaying the status of all of these commands, i.e. variables, user-functions, turtle-statuses, and other data. 
- Allow user to change preferences and turtle behavior graphically, via buttons and drop-down menus
- Support programming in several languages beyond just English

### How to Add New Features/Commands

#### Adding a New Command to the Language:

Adding a new command to the language has a range of possible difficulties and required changes.
The following would be completely mandatory:
    - A new SlogoNode class which specifies the behavior of the SlogoNode on execution
    - A small addition to the properties files with the new command's possible names as a string, and adaptations for different languages.
For a command which takes atypical arguments (i.e. something like dotimes, for, to, etc.), one might also need to make some changes to TreeBuilder because TreeBuilder uses the type of the parent SlogoNode to decide how to build the tree. 


#### New Front-End Component:

To add a new front-end component, one can simply add a new SceneElement subclass, determine its relationships to the other SceneElements (perhaps it needs to have another SceneElement or be had by one), then add it to the List of SceneElements in SlogoView. As long as the positions work out, it should work fine. Depending on what it does, you might need to add more functionality if it needs to access the back-end, but a basic element requires very little work. 

### Major Design Choices

#### Front-End

The Front End consists primarily of the SLogoView class and its elements. A SlogoView object contains a map of Integers to Turtles, representing the Turtles and their IDs. It also contains a List of SceneElements. These SceneElements represent all of the different fields for display in the main window, i.e. console, command history, current turtle states, and the turtle display. The relationship between these SceneElements and the SlogoView is an Observer-Observable relationship, so the SlogoView can respond to changes in the SceneElements instantaneously. Each SceneElement object does this through its GetField method because a call to the update() method of SlogoView (the update method is the mechanism by which Observables update their observers -- they call each of the Observers in their Observer list's update method) uses the getField method of a SceneElement to redraw all of the SceneElements. 

#### Back-End

The backend of our project consists of three main parts: The CommandFactory, the TreeBuilder, and the Commands themselves. 

1. Command Factory
    The Command Factory takes in a "sanitized" array of strings(all lowercase, no impermissible characters, -- this is the front end's job to clean it up and convert the user input to an array of strings) and converts it into an equally-sized array of SlogoNodes. SlogoNode implements Command, an interface which includes two methods, getValue and getExecute, which get the return value and perform necessary execution functions, respectively. 
    To do this, it needs access to the function map contained contained by the Main class. This is passed in the update() method of Main. 
    The Command Factory uses its own internal instance of the NodeBuilder class to check the strings to see which SlogoNode should be built, and then it calls the appropriate method in NodeBuilder to build that node. The NodeBuilder class uses reflection to do so. 
    
2. TreeBuilder
    The TreeBuilder then takes in the array and builds a tree for execution, in which each SlogoNode has the correct number of children (i.e. a Forward node has one child and an Add node has two). It does this recursively using the Build and BuildList methods. Essentially, the more complex expressions are handled using a massive switch statement which checks the type of the SlogoNode and then builds the appropriate single node and list groups. The built tree is then passed to the TreeReader, which eecutes the tree. 
    
3. Commands
    Each SlogoNode has a getValue and getExecute method. Generally, the getExecute method calls another, private method which performs any necessary operations (such as moving the turtle) while the getValue method calculates the return value (such as calculating a sum). The getValue method gets its value by calling getExecute on its children, so the execution proceeds "backwards". 
    Each SlogoNode requires the Variable Map, Function Map, and Turtle Map from main, because these are the data structures which can be directly affected by the commands. These are directly passed in by Main.
    
4. Another decision we made was to separate the creation of the individual SlogoNodes and the tree composed of them. I built the TreeBuilder, so I can say with confidence that it was a complex task. Working with the full array means that you always need to be keeping track of which node doesn't have the right number of children yet. Another idea that we considered was building the tree within the CommandFactory class. I think that this could have simplified the tree building aspect of the program because you could consider each node as it was built, always working with the correct node by default without having to use the buildcounter variable I ended up using. However, it would have made the CommandFactory class much longer and harder to read. On the whole, I think our design decision was a justifiable one, but I would have liked to see how a different implementation could work as well. Each design was equally encapsulated because neither changes the nodes, just ties them together. On the whole, I would consider the two equally valid. 
    

### Assumptions to Simplify/Resolve Ambiguities
    
- Our tell command creates all turtles with ids up to the value entered in a tell command. I.E. if a user enters tell [ 100 ] turtles 0-100 will be created. 
- Brackets are followed by and preceded by a space, e.g. [ x ]
- All commands will have the necessary number of children, e.g. sum 10 will not be entered (error will be thrown)
- Subclasses (Instances of a Command Node) will all properly implement the two methods getValue and getExecute, and any other methods will be private and internal
- Every Command Node will exist within one of the currently existing packages; edits will need to be made to change that
- User does not want the turtle off screen; currently the built in behavior is to cancel the movement that moves the turtle out of the window.

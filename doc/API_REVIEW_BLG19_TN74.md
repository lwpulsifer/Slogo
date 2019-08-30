# SLOGO API Review

## Part 1
1.  What about your API/design is intended to be flexible?
    * Brandon:
        * The API for the interpreter is flexible because string checking can occur within it. Because the interpreter is simply passed a string representing the instruction and an object to operate on, and passes back an integer, with the necessary changes already computed for the object, it is flexible to any input given by the user. Should the user's input be invalid, the integer returned will reflect that. Thus, any user input can be "taken". It is further flexible because changing the possible user instructions only needs a reflected change in the interpreter's accessible methods to build an instruction. The methods called by the interpreter inherently edit the objects themselves, so the gui always has access to the most current data. 
    * Trishul:
        * Our design is flexible because it minimizes the communication of information from different parts of the program to only exactly what they need. The front end receives information from the back end about what it needs to display - and thats it. Our backend would be flexible enough to allow any number of objects with properties on how they could change, but the interface would be flexible enough such that the front end would still be able to handle all of it.
        * Our exception passing is also meant to be flexible because we can pass anything that extends an abstract ``SLOGOValid`` class. The toString for this ``SLOGOValid`` object would display information about if the command worked or if it failed. We could extend the program later to incorporate why the program failed later if we desired. 
2.  How is your API/design encapsulating your implementation decisions?
    * Brandon: 
        * This design encapsulates the implementation decisions because this design delegates tasks in a representative and logical way; data access and edits are done only by the classes and methods that require them, and logical operations are done in pieces. For example, the console does not interact with the objects in any way except for passing them to the interpreter; additionally, the only interaction with the backend is through .parseInstruction. 
    * Trishul:
        * The API design we have encapsulates information into only the parts of the program that need to have access to it. The backend model, for example, has information about all objects in the environment's ecosystem. The controller is the only one that has access to error checking commands and won't even bother the backend if it received an invalid command. The front end is so encapsulated that its sole purpose is to display information - which it gets from the backend through a public API. This API ensures the minimal amount of info is passed between the two so there is no chance of the frontend being able to modify backend data.
3.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * Brandon: 
        * The most likely error case that will need to be handled is the case of invalid instructions. In this case, a reasonable solution would be to return a value that represents this error. For example, on sucessful execution of an instruction, the parseInstruction method will simply return a negative 1. In the case of an error, it will return the integer corresponding to where in the instruction it is breaking. This is good for both debugging and for design; there's no way for the parseInstruction to break on the -1st line of code, and the front end only needs to know whether the instruction broke (and where) or not.
    * Trishul:
        * We will be defining a ``SLOGOInvalid`` class which defines if an operation was successful or not. A string will be passed from the front end to the interpreter, and the interpreter will return to it an instance of the ``SLOGOValid`` class. If the operation is successful, this object will have a toString that suggests it was a success and would display a return value. If the interpreter believes the command is invalid, it will return a ``SyntaxException`` object to the front end. If the interpreter believes the command is valid, it passes it to the backend which then tries to execute it. If the backend determines the command is invalid it will pass its own flavor of the ``SLOGOValid`` object to the interpreter which will pass it to the front end. The front end then figures out how to display this ``SLOGOValid`` object 
4.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    * Brandon:
        * I think this design is good because this design allows the logical changes to be reserve to the back end, and the addition of additional instructions or rules to be interpreted necessitates a change only in one class (potentially two in terms of the call to that method). Within the interpreter class, there will be a multitude of APIs in order to pass data properly, but all of this can be encapsulated within the logical operations of the backend. 
    * Trishul:
        * I think the design is good because it encapsulates functionality very well. Each part of the program has only the information it needs and gets only the information it needs from other portions of the program. The design becomes flexible because changing a part of the program is easy and would not entail a substnatial amount of change in the code. There is a low likelihood that we would ever have to scrap the topmost design of our program to incorporate a feature.
     
## Part 2     
1.  How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
     * Brandon:
         * Implement a factory class that can take in any object and return a new object that only contains the information the front end needs to display (position, angle, color, etc). This is a good pattern to implement because it is extremely flexible (extracts data out of an object, regardless of what that object is) and encapsulates/isolates data so that the front end only has access to the data it needs rather than all the possible data. Naturally, an abstract class with image properties (pen up, position, etc) is useful so that all the methods can take in a singular, abstract object. 
      * Trishul:
          * Our design implements model-view-controller architecture at the highest level. Our front-end classes form the view. It has a two-way communication channel with the interpreter (the controller) where the view sends a string (a command) and the interpreter determines if it is valid(the ``SLOGOValid`` object). It also has a one way of channel of communication with the Model where the model sends info about what needs to be displayed to the front end through an API.
          * 
2.  What feature/design problem are you most excited to work on?
    * Brandon:
        * I'm excited to work on the physical implementation of the interpreter because it's really interesting to see how I can build what is essentially a language processor. Being able to better understand how functions can be defined and the basis upon which an entire programming language can be built.
     * Trishul: 
         * I am working on the front end and I most excited to implement the environment displayer information because I think it would be really cool as a program debugger. Displays like this are often the most helpful part of debugging and I am interested to see how ourws will work.
3.  What feature/design problem are you most worried about working on?
     * Brandon:
         * The implementation of the interpreter and making it flexible enough to handle whatever the console throws it. I still don't entirely understand the assignment/project from a high level to be confident in our implementation strategy. This essentially manifests itself in the less fleshed out data flow of our project.
     * Trishul:
         * I am most worried about working on the displayer for our project. I think to make it truly robust, we should be able to have it display what is effectively an infinite zoomable grid inside the screen. I think that will quite a challenge to build but certainly dooable and entirely a front-end problem
4.  Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
    * Brandon:
        1. Able to interpret a single instruction, with built-in detection for whether it is a math operation or a object operation.
        2. Able to combine and save math operation(s) and object operation(s)
        3. Able to tell the user which character the instruction broke on
        4. Able to only pass the front end the data that it needs in the form of a "ghost" object
        5. Able to store variables in a database.
    * Trishul
        * Display turtle on visual displayer
        * Dispaly exception to the user that tells them if their command was valid or not
        * Access immutable copies of data from the back end
        * Change display color of background
        * Change display image of turtle
# API\_REVIEWa\_lp139_bmc41.md

## Brendan's API

### Part 1:
##### External Front-End
* Four classes
    1. Main UI - display objects
    2. Command History View - display all recent commands and allow interaction within them 
    3. SimInfo - Container for back end and internal front end, canvas, turtle object
    4. ErrorView - If any exception occurs (bad commands, outside of canvas range, etc), this will handle it and 

Brendan's Responsibilities

Command History and ErrorView

Flexibility
* Add one class alone for each command
* ErrorView doesn't need to know the commands because all that it displays is the text
    * Just needs to pass the commands to error checker classes 
    * and display if there's an error
    * or if there's not an error
* CommandHistory only updates if the command entered was valid, so flexible in that it doesn't need to know any more about the command than what it is.
    * Interaction with commands is also limited to passing the responsibility to another class

Encapsulation
* No class in Brendan's API needs to know anything more than which command was executed
    * So no class knows anything more than it needs to 

Exceptions or Error Cases
* ErrorView class needs to be able to catch exceptions thrown by backend, depending on which kind of exception occurred. 
    * Command Syntax error exception -- spits text back out to show why an issue
    * Logical command error (syntax correct, but current data says command can't be executed, i.e. turtle at top of grid trying to move forward)
* CommandHistory view shouldn't need to throw or catch anything because all possible errors will have been handled by the time anything gets to it. All exception handling related to commands will happen in back end and all of the commands passed to CommandHistory will be guaranteed to be valid. 

Why is it good?
* Makes it easy to add to program 
    * No need to change any of Brendan's stuff in order to:
        * Add a new command
        * Execute a new command

### Part 2:


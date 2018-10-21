# Running the code
Author: Andreas Slovacek
Date: 21 October 2018



## System Requirements
* Unix based OS
* JRE 1.5+
* Tested on OSX 10.13.4



## Classes


### FiniteAutomaton
Simulates a finite automaton, with default type DFA.

### NFA
Simulates a non-deterministic finite automaton, and implements a recursive approach found 
[here](https://condor.depaul.edu/ichu/csc416/notes/notes3/nfa/nfa.html)

### JackProcessor
Reads the input .jack character by character.  The state diagram of this machine is *Figure: JackProcessor*.


## Files


### Input Jack Files
These can be anywhere in the PJ03 Directory, must end in *_.jack_* and must not have 
*test* in the name.

### results/
Contains the *_.tok_* and *_.log_* files 


### code/ 

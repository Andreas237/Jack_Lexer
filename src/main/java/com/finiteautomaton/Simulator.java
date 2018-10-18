package com.finiteautomaton;


import java.util.ArrayList;
import lombok.Data;







///< Finite Automaton (FiniteAutomaton)
/**
* @author andreasslovacek
* This class implements States, Alphabet, Transitions, and validity of a finite automaton
*/
@Data
public class Simulator{

	
	private FiniteAutomaton automaton;							///< Finite automaton for this simulation
	private ArrayList<String> inputStrings;			///< Strings from parsed input file 
	private ArrayList<String> acceptedStrings;		///< Strings accepted by this simulation
	private State currentState;						///< Current state of the simulated FiniteAutomaton
	private ArrayList<Integer> finalStates;			///< States related to Keywords
	private Integer finalState;						///< Final state of the machine throughout sim
	private ArrayList<String> rejectedStrings;		///< Strings not accepted by the FiniteAutomaton
	private ArrayList <StringResult> stringResults;	///< Container for all string:token pairs







	///< StringResult
	/*!
	 * Container for .tok output
	 */
	public class StringResult{
		public String string;
		public String token;
	}// end public class StringResult







	///< Simulator
	/*!
	 * Initialize the member variables, then run the simulation
	 */
	public Simulator(FiniteAutomaton automaton, ArrayList<String> inputStrings){
		this.automaton = automaton;
		this.inputStrings= inputStrings;
		acceptedStrings = new ArrayList<String>();
		rejectedStrings = new ArrayList<String>();
		stringResults = new ArrayList<StringResult>();
		finalStates = new ArrayList<Integer>();
		finalState = null;
		simulate();
	}// end public Simulator(FiniteAutomaton automaton, ArrayList<String> inputStrings)






	///< simulate
	/*!
	 * Use the input strings provided to the constructor to run a simulation
	 * of the FiniteAutomaton provided to the contructor.  Stash accepted/rejected strings;
	 * tokens associated with those strings; states of the machine
	 */
	private void simulate(){
		for(String inputString: inputStrings){
			if(automaton(new State(0),inputString,0) == true) {
				acceptedStrings.add(inputString);
			}
			else {
				rejectedStrings.add(inputString);
			}
			
			StringResult sr = new StringResult ();
			sr.string = inputString;
			
			if(JackTokenizer.TokenMap.containsKey(finalState)) {
				sr.token = JackTokenizer.TokenMap.get(finalState);
			}
			else {
				sr.token = "BADTOKEN";
			}
				
			
			stringResults.add(sr);
			finalStates.add(finalState);
			finalState = null;
		}
	}
	
}// end public class Simulator

package com.finiteautomaton;

import com.jackanalyzer.MachineDescription;
import java.util.ArrayList;
import java.util.Collections;

import lombok.Data;







///< Finite Automaton (FiniteAutomaton)
/**
* @author andreasslovacek
* This class implements States, Alphabet, Transitions, and validity of a finite automaton
*/
@Data
public class Simulator{

	
	private NFA automaton;							///< Finite automaton for this simulation
	private ArrayList<String> inputStrings;			///< Strings from parsed input file
	private ArrayList<String> acceptedStrings;		///< Strings accepted by this simulation
	private ArrayList<Integer> finalStates;			///< States related to MachineDescription
	private Integer finalState;						///< Final state of the machine throughout sim
	private ArrayList<String> rejectedStrings;		///< Strings not accepted by the FiniteAutomaton
	private ArrayList <ResultPair> resultPair;		///< Container for all string:token pairs
	private static Byte epsilon = 96;







	///< ResultPair
	/*!
	 * Container for .tok output
	 */
	public class ResultPair{
		public String string;
		public String token;
	}// end public class ResultPair







	///< Simulator
	/*!
	 * Initialize the member variables, then run the simulation
	 */
	public Simulator(NFA automaton, ArrayList<String> inputStrings){
		this.automaton = automaton;
		this.inputStrings= inputStrings;
		acceptedStrings = new ArrayList<String>();
		rejectedStrings = new ArrayList<String>();
		resultPair = new ArrayList<ResultPair>();
		finalStates = new ArrayList<Integer>();
		finalState = null;
		//simulate();
		//simulate2();
	}// end public Simulator(FiniteAutomaton automaton, ArrayList<String> inputStrings) 
	
	
	
	
	
	
	
	
	
	///< recursiveFaSim(State startState, String input, int pos)
	/*!
	 * "Recursive Implementation of NFAs" from DePaul University
	 * https://condor.depaul.edu/ichu/csc416/notes/notes3/nfa/nfa.html  
	 */
	private boolean recursiveFaSim(State startState, String input, int pos){
		
		
		ArrayList<State> nextStates 	= new ArrayList<State>();
		Transition epsilonTransition 	= new Transition(startState, epsilon);
		
		
		// for each epsilon-transition from state A to some state B do
		if(this.getAutomaton().getTransitions().containsKey(epsilonTransition)){
			System.out.println("Sim: contains epsilonTransition");
			nextStates.addAll(this.getAutomaton().getTransitions().get(epsilonTransition));
			Collections.sort(nextStates); // Thanks Comparable!
			
			// Process epsilon transitions
			for(State nextState: nextStates){
				// if nfa (B) then return True
				// Recurse through the input string checking the accept status
				if(recursiveFaSim(nextState, input, pos+1) == true){
					System.out.println("Sim: returning true for state " + nextState);
					return true;
				}// end if(new NFA(nextState, input, pos).getAcceptStatus() == true)
	        
			}// end for(State nextState: nextStates)
		}// end for(State nextState: nextStates)
		else{
			System.out.println("Sim doesn't contain epsilon transition");
		}
		
		
		
		
		//if there is a next symbol then
		if(pos < input.length()){
			
			//read next symbol
			Byte symbol = new String(""+input.charAt(pos)).getBytes()[0];
			
			//for each x transition from state A to some state B do
			Transition transition = new Transition(startState, symbol);
			System.out.print("Sim: transition= " + transition.toString());
			System.out.println("\t\thas transition " + this.getAutomaton().getTransitions().containsKey(transition));
			if(this.getAutomaton().getTransitions().containsKey(transition)){
				System.out.println("Sim: making the transition: " + this.getAutomaton().getTransitions().get(transition).toString());
				for(State nextState: this.getAutomaton().getTransitions().get(transition)){
					//if nfa (B) then return True
					if(recursiveFaSim(nextState,input,pos+1) == true){
						return true;
					}// end if(new NFA(nextState,input,pos+1).getAcceptStatus() == true)
		        }// end for(State nextState: this.transitions.get(transition))
			}// end if(this.transitions.containsKey(transition))
				
			else
				return false;
		}// end if(pos < input.length())	
			
			
			
		//if there is NOT a next symbol then
		else{
			//if A is a final state then return True;	
			if(this.getAutomaton().getAcceptStates().contains(startState)){
				if( finalState == null )
					finalState = startState.state;
				return true;
			}
			else
				return false;
		}// end else --if there is NOT a next symbol then
		return false;
	}// end private boolean recursiveFaSim(State startState, String input, int pos)






	///< simulate
	/*!
	 * Use the input strings provided to the constructor to run a simulation
	 * of the FiniteAutomaton provided to the contructor.  Stash accepted/rejected strings;
	 * tokens associated with those strings; states of the machine
	 */
	private void simulate(){
		MachineDescription tempMachine = new MachineDescription();
		for(String inputString: inputStrings){
			if( new NFA(new State(0),inputString,0).getAcceptStatus() == true){
				acceptedStrings.add(inputString);
				System.out.println("Simulator: Added " + inputString + " to acceptedStrings");
			}
			else{
				//System.out.println("Simulator: Added " + inputString + " to rejectedStrings");
				rejectedStrings.add(inputString);
			}

			
			
			/*
			 * Fill in ResultPairs. if FA contains the final state then get 
			 * the final state token otherwise return bad token
			 */
			ResultPair pair = new ResultPair ();
			pair.string = inputString;
			if(MachineDescription.getAcceptMap().containsKey(finalState))
				pair.token = tempMachine.getAcceptMap().get(finalState);
			else
				pair.token = "BAD TOKEN";
				
			
			resultPair.add(pair);
			finalStates.add(finalState);
			finalState = null;
		}// end for(String inputString: inputStrings)
	}// end void simulate()






	///< simulate2
	/*!
	 * Use the input strings provided to the constructor to run a simulation
	 * of the FiniteAutomaton provided to the contructor.  Stash accepted/rejected strings;
	 * tokens associated with those strings; states of the machine
	 */
	private void simulate2(){
		for(String inputString: inputStrings){
			if( recursiveFaSim(new State(0),inputString,0) == true){
				acceptedStrings.add(inputString);
				System.out.println("Simulator: Added " + inputString + " to acceptedStrings");
			}
			else{
				//System.out.println("Simulator: Added " + inputString + " to rejectedStrings");
				rejectedStrings.add(inputString);
			}

			
			
			/*
			 * Fill in ResultPairs. if FA contains the final state then get 
			 * the final state token otherwise return bad token
			 */
			ResultPair pair = new ResultPair ();
			pair.string = inputString;
			
			
			if(MachineDescription.getAcceptMap().containsKey(this.finalState))
				pair.token = MachineDescription.getAcceptMap().get(finalState);
			else
				pair.token = "BAD TOKEN";
				
			
			resultPair.add(pair);
			finalStates.add(finalState);
			finalState = null;
		}// end for(String inputString: inputStrings)
	}// end void simulate2()
	
}// end public class Simulator

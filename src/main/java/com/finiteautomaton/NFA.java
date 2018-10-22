package com.finiteautomaton;

import java.util.ArrayList;
import java.util.Collections;


///< NFA
/*!
 * NFA implementation of a Finiate Automaton
 */
public class NFA extends FiniteAutomaton{

	
	private Transition eplisonTrans;		///< Epsilon transition def
	private ArrayList<State> nextStates;	///< Next states achieved in this FA
	
	
	
	
	
	
	
	
	
	///< NFA()
	/*!
	 * Constructor
	 */
	public NFA(){
		super();
	}// end public NFA() 
	
	
	
	
	
	
	
	
	
	///< NFA(State startState, String input, int pos)
	/*!
	 * "Recursive Implementation of NFAs" from DePaul University
	 * https://condor.depaul.edu/ichu/csc416/notes/notes3/nfa/nfa.html  
	 */
	public NFA(State startState, String input, int pos /**<Position in current string>*/){
		
		
		this.nextStates 	= new ArrayList<State>();
		this.eplisonTrans 	= new Transition(startState, new Byte((byte) 96));

		// for each epsilon-transition from state A to some state B do
		if(this.transitions.containsKey(eplisonTrans)){
			System.out.println("NFA: contains epsilonTransition");
			// Wait until needed to define
			nextStates.addAll(this.transitions.get(eplisonTrans));
			Collections.sort(nextStates); // Thanks Comparable!
			
			// Process epsilon transitions
			for(State nextState: nextStates){
				
				// if nfa (B) then return True
				// Recurse through the input string checking the accept status
				if(new NFA(nextState, input, pos+1).getAcceptStatus() == true){
					
					this.setAcceptStatus(true);
				}// end if(new NFA(nextState, input, pos).getAcceptStatus() == true)
	        
			}// end for(State nextState: nextStates)
		}// end for(State nextState: nextStates)
		
		
		
		
		//if there is a next symbol then
		if(pos < input.length()){
			
			//read next symbol
			Byte symbol = new String(""+input.charAt(pos)).getBytes()[0];
			
			//for each x transition from state A to some state B do
			Transition transition = new Transition(startState, symbol);
			System.out.println("NFA: new transition [symbol:" + transition.getSymbol() + "][startState:" + transition.getCurrentState() + "]");
			if(this.transitions.containsKey(transition)){
				System.out.println("NFA: making the transition: " + this.transitions.get(transition).toString());
				for(State nextState: this.transitions.get(transition)){
					//if nfa (B) then return True
					if(new NFA(nextState,input,pos+1).getAcceptStatus() == true){
						this.setAcceptStatus(true);
					}// end if(new NFA(nextState,input,pos+1).getAcceptStatus() == true)
		        }// end for(State nextState: this.transitions.get(transition))
			}// end if(this.transitions.containsKey(transition))
				
			else
				this.setAcceptStatus(false);
		}// end if(pos < input.length())	
			
			
			
		//if there is NOT a next symbol then
		else{
			//if A is a final state then return True;	
			if(this.getAcceptStates().contains(startState)){
				if( this.getFinalState() == null )
					this.setFinalState(startState.state);
				this.setAcceptStatus(true);
			}
			else
				this.setAcceptStatus(false);
		}// end else --if there is NOT a next symbol then
		this.setAcceptStatus(false);
		this.setType();
	}// end public NFA(State startState, String input, int pos)
		
}// end public class NFA extends FiniteAutomaton

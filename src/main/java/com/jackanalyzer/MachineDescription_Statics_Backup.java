package com.jackanalyzer;





import lombok.Data;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import com.finiteautomaton.*;



/**
 * @author andreasslovacek
 * This class generates the byte byterings for every keyword
 */
@Data
public class MachineDescription_Statics_Backup {
	
	@Getter public static HashMap<Integer, String> AcceptMap;	///< Map of accept state : token type
	private NFA machine;								///< Machine described by assignment
	
	
	
	
	
	
	
	
	
	
	
	///< MachineDescription
	/*!
	 * Add accept states paired to keywords to AcceptMap   
	 */
	public MachineDescription_Statics_Backup(){
		this.machine = new NFA();
		setStates();
		setAcceptMap();		// Keywords to accept states
		setAcceptStates();
		setAlphabet();
		setTransitions();
		MachineDescription_test();
	}// end public MachineDescription()
	
	
	
	
	
	
	
	
	
	
	
	///< MachineDescription_test
	private void MachineDescription_test(){
		//setAlphabet_test();
		//setStates_test();
		//setAcceptStates_test();
		//setAcceptMap_test();
		setTransitions_test();
	}// end MachineDescription_test()
	
	
	
	
	
	
	
	
	
	
	
	///< createTransition(Byte[] str, int[] inStates, boolean finish)
	/*!
	 * Given one of the input byte sequences, and states start/end states
	 * add the transition to the machine
	 */
	private static void createTransition(NFA machine, Byte[] str, int[] inStates, boolean finish){
		
		State startState, finalState;
		Byte symbol;
		
		// given arrays of sequences of states and the transition symbols add them to the machine
		for(int i = 0; i<= inStates.length-2; i++){
			startState 		= 	new State(inStates[i]);
			finalState 		= 	new State(inStates[i+1]);
			
			if( i == (inStates.length-2) && finish )
				symbol = (Byte) Integer.valueOf((byte)96).byteValue();
			else
			 	symbol = (Byte) Integer.valueOf(str[i]).byteValue();

			machine.addTransition(startState, finalState, symbol);
			
		 }// end for(int i = 0; i<= inStates.length -2; i++)
		
		
		
	}// end void createTransition(Byte[] str, int[] inStates, boolean finish)
	
	
	
	
	
	
	
	
	
	
	
	///< createTransitions(Byte[] str, int[] states, boolean finish)
	/*!
	 * Generate multiple transitions if there are many start-final states
	 */
	private static void createTransitions(NFA machine, Byte[] str, int[] states, boolean finish){
		
		int i = 0;
		
		while(i < str.length)
			createTransition(machine, new Byte[]{str[i++]},states,false);
		
	}// end void createTransitions(Byte[] str, int[] states, boolean finish)
	
	
	
	
	
	
	
	
	
	
	///< setAcceptMap
	/*!
	 * Each keyword has certain accept states. Set the state number,
	 * which is an accept state, matched to the keyword 
	 */
	private void setAcceptMap(){
		AcceptMap = new HashMap<Integer, String>();
		
		// Integer accept states 2-11
		for( int i = 2; i <= 11; i++ )
			AcceptMap.put(i, "INTEGER");
		
		
		// Other keywords only one accept state
		AcceptMap.put(34, "KW_CONST");
		AcceptMap.put(48, "KW_TYPE");
		AcceptMap.put(63, "KW_VARDEC");
		AcceptMap.put(90, "KW_SUBDEC");
		AcceptMap.put(111, "KW_VAR");
		AcceptMap.put(112, "KW_VOID");
		AcceptMap.put(133, "KW_CLASS");
		AcceptMap.put(114, "KW_LET");
		AcceptMap.put(115, "KW_IF");
		AcceptMap.put(116, "KW_ELSE");
		AcceptMap.put(122, "KW_WHILE");
		AcceptMap.put(125, "KW_DO");
		AcceptMap.put(132, "KW_RETURN");
		AcceptMap.put(135, "SY_LPAREN");
		AcceptMap.put(137, "SY_RPAREN");
		AcceptMap.put(139, "SY_LBRACKET");
		AcceptMap.put(141, "SY_RBRACKET");
		AcceptMap.put(143, "SY_LBRACE");
		AcceptMap.put(145, "SY_RBRACE");
		AcceptMap.put(147, "SY_SEMI");
		AcceptMap.put(149, "SY_PERIOD");
		AcceptMap.put(151, "SY_COMMA");
		AcceptMap.put(153, "SY_EQ");
		AcceptMap.put(155, "SY_MINUS");
		AcceptMap.put(157, "SY_NOT");
		AcceptMap.put(159, "SY_OP");
		AcceptMap.put(161, "IDENT");
		AcceptMap.put(164, "STRING");
					
	}// end void setAcceptMap()
	
	
	
	
	
	
	
	
	
	
	///< setAcceptMap_test()
	private void setAcceptMap_test(){
		System.out.println("======================================================");
		System.out.println("AccessMap Keys: "+this.AcceptMap.keySet().toString());
		System.out.println("======================================================");
		System.out.println("AccessMap Values: "+this.AcceptMap.values().toString());
		System.out.println("======================================================");
		System.out.println("AccessMap Pairs: "+this.AcceptMap.entrySet().toString());
		System.out.println("======================================================");
	}// end void setAcceptMap_test()
	
	
	
	
	
	
	
	
	
	
	///< setAcceptStates()
	/*!
	 * Add the accept states to the machine
	 */
	private void setAcceptStates(){ this.machine.setAcceptStates(AcceptMap.keySet());}
	
	
	
	
	
	
	
	
	
	
	///< setAcceptStates_test()
	private void setAcceptStates_test(){	System.out.println(this.machine.getAcceptStates().toString());}
	
	
	
	
	
	
	
	
	
	
	///< setAlphabet()
	/*!
	 * Add the alphabet in bytes to the machine
	 */
	private void setAlphabet(){
		
		Set<Byte> temp = new HashSet<Byte>();
		
		for(int i = 0; i <= 127; i++)
			temp.add((Byte)Integer.valueOf(i).byteValue());
		 
		this.machine.setAlphabet(temp);
		
	}// end void setAlphabet()
	
	
	
	
	
	
	
	
	
	
	///< setAlphabet_test()
	/*!
	 * Add the alphabet in bytes to the machine
	 */
	private void setAlphabet_test(){ System.out.println(this.machine.alphabetToString()); }// end void setAlphabet()
	
	
	
	
	
	
	
	
	
	
	///< setStates()
	/*!
	 * Add all the states from state diagram.
	 */
	private void setStates(){
		
		Set <State> temp = new HashSet<State>();
		
		for(int i=0; i <= 164; i++)
			temp.add(new State(i));
		
		this.machine.setStates(temp);
		
	}// end void setStates()
	
	
	
	
	
	
	
	
	
	
	///< setStates_test()
	private void setStates_test(){ System.out.println(this.machine.getStates().toString());}
	
	
	
	
	
	
	
	
	///< setTransitions
	/*!
	 *  Create epsilon transitions leaving the start state.
	 *  Create transitions for each possible keyword
	 *   
	 */
	private static NFA setTransitions(){
		
		NFA machine = new NFA();
		
		State startState, finalState;				///< Start and end states for transitions added to this machine
		Byte symbol;								///< Symbol for transitions added to this machine
		Byte[] byter ;								///< Array for sequences from state diagram
		int[] states; 								///< States relating to this keyword
		
		
		
		
		//======== Epsilon Transitions from start ========
		int startStates[] = {1,16,35,51,64,91,94,98,102,105,107,117,123,126,134,136,138,140,142,144,146,148,150,152,154,156,159,160,162};
		symbol = (Byte) Integer.valueOf((byte)96).byteValue();
		Transition t = new Transition(new State(0), symbol);
		for(int i : startStates){
			startState = new State(0);
			finalState = new State(i);
			machine.addTransition(startState, finalState, symbol);
		 }// end for(int i : startStates)
		
		//======== KW_CONST ======== 
		
		//	true
		byter	= new Byte[]	{'t','r','u','e'};
		states 	= new int[] 	{16,17,18,19,20,34};
		createTransition(machine,byter, states, true);
		
		// false
		byter	= new Byte[]	{'f','a','l','s','e'};
		states	= new int[] 	{16,21,22,23,24,25,34};
		createTransition(machine,byter, states, true);
		
		// null
		byter	= new Byte[]	{'n','u','l','l'};
		states 	= new int[] 	{16,26,27,28,29,34};
		createTransition(machine,byter, states, true);
		
		// this
		byter	= new Byte[]	{'t','h','i','s'};
		states 	= new int[] 	{16,30,31,32,33,34};
		createTransition(machine,byter, states, true);
		
		
		
		//======== KW_TYPE ======== 
		
		//int
		byter	= new Byte[]	{'i','n','t'};
		states 	= new int[] 	{35,36,37,38,48};
		createTransition(machine,byter, states, true);
		
		//char
		byter	= new Byte[]	{'c','h','a','r'};
		states 	= new int[] 	{35,39,40,41,42,48};
		createTransition(machine,byter, states, true);
		
		//boolean
		byter	= new Byte[]	{'b','o','o','l','e','a','n'};
		states 	= new int[] 	{35,43,44,45,46,49,50,47,48};
		createTransition(machine,byter, states, true);
		
		
		
		//======== KW_VARDEC ======== 
		//static
		byter	= new Byte[]	{'s','t','a','t','i','c'};
		states 	= new int[] 	{51,52,53,54,55,56,57,63};
		createTransition(machine,byter, states, true);
		
		//field
		byter	= new Byte[]	{'f','i','e','l','d'};
		states 	= new int[] 	{51,58,59,60,61,62,63};
		createTransition(machine,byter, states, true);
		
		
		
		//======== KW_SUBDEC ======== 
		//constructor
		byter	= new Byte[]	{'c','o','n','s','t','r','u','c','t','o','r'};
		states 	= new int[] 	{64,65,66,67,68,69,70,71,72,73,74,75,90};
		createTransition(machine,byter, states, true);
		
		//function
		byter	= new Byte[]	{'f','u','n','c','t','i','o','n'};
		states 	= new int[] 	{64,76,77,78,79,80,81,82,83,90};
		createTransition(machine,byter, states, true);
		
		//method
		byter	= new Byte[]	{'m','e','t','h','o','d'};
		states 	= new int[]		{64,84,85,86,87,88,89,90};
		createTransition(machine,byter, states, true);
		
		
		
		
		//======== KW_VAR ======== 
		byter	= new Byte[]	{'v','a','r'};
		states 	= new int[] 	{91,92,93,111};
		createTransition(machine,byter, states, false);

		
		
		//======== KW_VOID ======== 
		byter	= new Byte[]	{'v','o','i','d'};
		states 	= new int[] 	{94,95,96,97,112};
		createTransition(machine,byter, states, false);

		
		
		//======== KW_CLASS ======== 
		byter	= new Byte[]	{'c','l','a','s','s'};
		states 	= new int[] 	{98,99,101,100,113,133};
		createTransition(machine,byter, states, false);
		
		
		
		//======== KW_LET ======== 
		byter	= new Byte[]	{'l','e','t'};
		states 	= new int[]		{102,103,104,114};
		createTransition(machine,byter, states, false);

		
		
		//======== KW_IF ======== 
		byter	= new Byte[]	{'i','f'};
		states 	= new int[]    	{105,106,115};
		createTransition(machine,byter, states, false);
		
		
		
		//======== KW_ELSE ======== 
		byter	= new Byte[]	{'e','l','s','e'};
		states 	= new int[]    {107,108,109,110,116};
		createTransition(machine,byter, states, false);
		
		
		
		//======== KW_WHILE ========
		 byter		= new Byte[] 	{'w','h','i','l','e'};
		 states 	= new int[]    {117,118,119,120,121,122};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== KW_DO ========
		 byter		= new Byte[] 	{'d','o'};
		 states 	= new int[]    {123,124,125};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== KW_RETURN ========
		 byter		= new Byte[] 	{'r','e','t','u','r','n'};
		 states 	= new int[]    {126,127,128,129,130,131,132};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_LPAREN ========
		 byter		= new Byte[] 	{'('};
		 states 	= new int[]    {134,135};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_RPAREN ========
		 byter		= new Byte[] 	{')'};
		 states 	= new int[]    {136,137};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_LBRACKET ========
		 byter		= new Byte[] 	{'['};
		 states 	= new int[]    {138,139};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_RBRACKET ========
		 byter		= new Byte[] 	{']'};
		 states 	= new int[]    {140,141};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_LBRACE ========
		 byter		= new Byte[] 	{'{'};
		 states 	= new int[]    {142,143};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_RBRACE ========
		 byter		= new Byte[] 	{'}'};
		 states 	= new int[]    {144,145};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_SEMI ========
		 byter		= new Byte[] 	{';'};
		 states 	= new int[]    {146,147};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_PERIOD ========
		 byter		= new Byte[] 	{'.'};
		 states 	= new int[]    {148,149};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_COMMA ========
		 byter		= new Byte[] 	{','};
		 states 	= new int[]    {150,151};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_EQ ========
		 byter		= new Byte[] 	{'='};
		 states 	= new int[]    {152,153};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_MINUS ========
		 byter		= new Byte[] 	{'-'};
		 states 	= new int[]    {154,155};
		 createTransition(machine,byter, states, false);
			
			
			
		//======== SY_NOT ========
		 byter		= new Byte[] 	{'~'};
		 states 	= new int[]    {156,157};
		 createTransition(machine,byter, states, false);
		
		
		
		//======== SY_OP ======== 
		byter	= new Byte[]	{'<','>','|','&','/','*','+'};
		states 	= new int[]		{158,159};
		createTransitions(machine,byter, states, false);
		
		
		
		//======== IDENT ========
		 byter	= new Byte[] 	{
				 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
				 '_'
				 };
		 states 	= new int[]    {160,161};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{
				 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
				 '_',
				 '0','1','2','3','4','5','6','7','8','9'
				 };
		 states 	= new int[]    {161,161};
		 createTransitions(machine,byter,states,false);

		//======== STRING ========
		 byter	= new Byte[] 	{'"'};
		 states = new int[]    	{162,163};
		 createTransition(machine,byter, states, false);
		 
		 // Much the rest of the string...
		 byter	= new Byte[253];
		 int pos=0;
		 Integer i=0;
		 while(i<256){
			 if(i != 10 && i != 34 && i.byteValue() != (byte)96){
				 byter[pos] = i.byteValue();
				 pos++;
			 }// end if(i != 10 && i != 34 && i.byteValue() != (byte)96)
			 i++;
		 }// end while(i<256)
		 states = new int[]    {163,163};
		 createTransitions(machine,byter,states,false);

		 //finish string
		 byter	= new Byte[] 	{'"'};
		 states = new int[]    	{163,164};
		 createTransition(machine,byter, states, false);
		 
		 
		 
		 //======== INTEGER ========
		 byter	= new Byte[] 	{'0'};
		 states 	= new int[]    {15,1};
		 createTransition(machine,byter, states, false);
		 byter	= new Byte[] 	{'1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {15,2};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'1','2'};
		 states 	= new int[]    {15,6};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'3'};
		 states 	= new int[]    {15,11};
		 createTransition(machine,byter, states, false);
		 byter	= new Byte[] 	{'0'};
		 states 	= new int[]    {1,1};
		 createTransition(machine,byter, states, false);
		 byter	= new Byte[] 	{'0'};
		 states 	= new int[]    {1,15};
		 createTransition(machine,byter, states, false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {1,2};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {2,3};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {3,4};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {4,5};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {6,7};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {7,8};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {8,9};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7','8','9'};
		 states 	= new int[]    {9,10};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'0','1'};
		 states 	= new int[]    {11,7};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'2'};
		 states 	= new int[]    {11,12};
		 createTransition(machine,byter, states, false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6'};
		 states 	= new int[]    {12,8};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'7'};
		 states 	= new int[]    {12,13};
		 createTransition(machine,byter, states, false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5'};
		 states 	= new int[]    {13,9};
		 createTransitions(machine,byter,states,false);
		 byter	= new Byte[] 	{'6'};
		 states 	= new int[]    {13,14};
		 createTransition(machine,byter, states, false);
		 byter	= new Byte[] 	{'0','1','2','3','4','5','6','7'};
		 states 	= new int[]    {14,10};
		 createTransitions(machine,byter,states,false);
		 
		 
		 return machine;
		
	}// end setTransitions()
	
	
	
	
	
	
	
	
	///< setTransitions_test()
	private void setTransitions_test(){
		HashMap<Transition,Set<State>> allTs = new HashMap<Transition,Set<State>>();
		allTs = machine.getTransitions();
		State start = new State(0);
		Byte symbol = (Byte) Integer.valueOf((byte)96).byteValue();
		int startStates[] = {1,16,35,51,64,91,94,98,102,105,107,117,123,126,134,136,138,140,142,144,146,148,150,152,154,156,159,160,162};
		Transition t;
		for(int i : startStates){
			//System.out.println("start state: " + i);
			symbol = (Byte) Integer.valueOf((byte)96).byteValue();
			t = new Transition(start, symbol);
			
		}
		
		//symbol = (Byte) Integer.valueOf((byte)96).byteValue();
		//t = new Transition(start, symbol);
		//System.out.println("Transition: "+ t.toString());// + "\t\t to states " + allTs.get(t).toString() );
		//System.out.println("Transition: "+this.machine.getTransitions().containsKey(t));
		
		for(Transition t1 : allTs.keySet())
			System.out.println("Transition: " + t1.toString() + "\t\t to states " + allTs.get(t1).toString() );
		//this.machine.printStringSelf();
	}// end void setTransitions_test()

}// end class MachineDescription

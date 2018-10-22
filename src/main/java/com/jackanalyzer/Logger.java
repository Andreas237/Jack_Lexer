package com.jackanalyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.finiteautomaton.Simulator;


///< Logger
/*!
* Given a file to write to and token:string pairs write them to the output token file
*/
class Logger{

	public Logger(File logFile, Simulator sim) throws IOException{
		FileOutputStream oStream = new FileOutputStream(logFile);
		@SuppressWarnings("resource")
		BufferedWriter hemmingway = new BufferedWriter(new OutputStreamWriter(oStream));
	
		
		// is valid?
		if(sim.getAutomaton().isValid())
			hemmingway.write("Valid: true\n");
		else
			hemmingway.write("Valid: false\n");
		
		// number of states
		hemmingway.write( "Number of states: " + sim.getAutomaton().getStates().size() + "\n");
		// alphabet
		hemmingway.write("Alphabet: " + sim.getAutomaton().alphabetToString() + "\n");
		// accepted strings
		hemmingway.write("Accepted Strings: " + sim.getAcceptedStrings().toString() + "\n");
		
		
	}// end public Logger(File logFile, Simulator sim)
}// end class Logger

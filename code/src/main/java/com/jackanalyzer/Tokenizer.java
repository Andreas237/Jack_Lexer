package com.jackanalyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import com.finiteautomaton.Simulator;


///< Tokenizer
/*!
 * Given a file to write to and token:string pairs write them to the output token file
 */
class Tokenizer{
	
	
	public Tokenizer(File tokenFile,  ArrayList<Simulator.ResultPair> resultPairs) throws IOException{
		FileOutputStream oStream = new FileOutputStream(tokenFile);
		BufferedWriter hemmingway = new BufferedWriter(new OutputStreamWriter(oStream));
		
		for(Simulator.ResultPair pair: resultPairs)
			hemmingway.write(pair.token +", " + pair.string + "\n");
		
	 
		hemmingway.close();
    }// end public Tokenizer(File tokenFile,  ArrayList<Simulator.ResultPair> resultPairs) 

}// end class Tokenizer

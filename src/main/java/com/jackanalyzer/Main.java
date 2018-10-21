package com.jackanalyzer;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;




class Main {

	/**
	 * Checks that an input file was given args[1]
	 * Creates a JackTokenizer with the input file
	 * Opens out.xml for writing
	 * 
	 * @param args should have length 2, with args[1] being an input file
	 */
	public static void main(String[] args) {

		// Exit with error if no input file specified
		if( args.length != 1 ) {
			System.out.println("ERROR:\tSpecify 1 input file");
			System.exit(1);
		}// end if( args[1] == null )
		else {
			System.out.println("Received " + args[0] + " as input jack file.");
		}// end else
		
		// If the the infile exists run the simulation
		JackAnalyzer ja = new JackAnalyzer(args[0]);
		ja.tokenCount("int".getBytes());
		
		CompilationEngine ce = new CompilationEngine();

		
		
		
	}// end public static void Main(String[] args)

}// end class Main

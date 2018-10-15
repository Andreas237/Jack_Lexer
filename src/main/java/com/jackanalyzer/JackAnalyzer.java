package com.jackanalyzer;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;

// Autogen functions
import lombok.Data;
import lombok.Getter;

@Data
public class JackAnalyzer extends Keywords {

	
	private String inFileName;			// File name for writing
	@Getter private FileReader inJack;	// File to read JACK from
	@Getter private FileWriter outFile;	// File to write output to
	private static HashMap CategoryMap; 		// type <byte, String>
	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor - run the program
	 */
	public JackAnalyzer( String fin ) {
		
		// Open the jack file for reading
		this.inJack = this.setInFilePointer(fin);
		if( getInJack() == null)
			System.out.println("Failed to read infile");
		else{
			System.out.println("read a file");
		}// end else
		
		
		
		// set the inFileName
		setInFileName(fin.replace(".jack", "") );
		
		// Delete existing output file if it exists
		removeExistingOutputFile();
		
		// Create a filewriter for output
		this.outFile = setOutFilePointer();
		
		// Call the Compilation Engine
		/*
		 * Can't use regex/substrings
		 * Instead use byte ops.
		 * Can I make a map of {(byte:char),(keyword)} and use same read
		 * and classify method as I did at CSUEB?
		 * FA Sim -> first read, then determine states at each KW
		 */
		
		
		
		
		
		// Close the file pointers
		try {
			this.inJack.close();
			this.outFile.close();
		} // end try
		catch (IOException e) {
			System.out.println("Caught an error closing file pointers");
			e.printStackTrace();
		}// end catch (IOException e)
		
	}// end public JackAnalyzer() 
	
	
	
	
	
	
	
	
	
	
	/**
	 * Delete output file if it exists.  Log to console if it 
	 */
	private void removeExistingOutputFile(){
		
		File f = new File(this.inFileName + ".xml");
		if( f.exists() ){
			if( !f.delete() )
				System.out.println("WARNING: Could not delete " + this.inFileName + ".xml");
		}// end if( f.exists() )
		else
			System.out.println("WARNING: " + f.getName() + " DOES NOT exists.");
		
	}// end private void removeExistingOutputFile() 
	
	
	
	
	
	
	
	
	
	
	/**
	 * Open an input file, throw error if no file given
	 */
	public FileReader setInFilePointer(String fin) {
		FileReader fr = null;
		
		if( !fin.endsWith(".jack") ){
			System.out.println("ERROR: Input file must be of type .jack");
			System.exit(1);
		}
		else
			System.out.println("Input file is " + fin);
		
		
		try{
			fr = new FileReader(fin) ;
		}// end try
		catch (FileNotFoundException e){
			System.out.println("ERROR: " + fin + "not found");
			System.exit(1);
		}//end catch
		return fr;
	} // end public FileReader getFilePointer(String fin)
	
	
	
	
	
	
	
	
	
	
	/**
	 * Set the output file as specified in Chapter 10 of Nisan and Schoken 
	 */
	protected FileWriter setOutFilePointer(){
		FileWriter fw = null;
		try{
			fw = new FileWriter( getInFileName() + ".xml" );
			return fw;
		}
		catch(IOException e) {
			System.err.println(" Something wrong in setOutFilePointer");
			System.err.println(e);
		}
		
		return fw;
	}// end protected void setOutFilePointer()
	
	

}// end class com.jackanalyzer

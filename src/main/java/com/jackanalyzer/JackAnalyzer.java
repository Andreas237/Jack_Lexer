package com.jackanalyzer;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;

// Autogen functions
import lombok.Data;
import lombok.Getter;

@Data
public class JackAnalyzer {

	
	private String inFileName;
	@Getter private FileReader inJack;
	@Getter private FileWriter outFile;
	
	
	
	
	
	
	
	
	
	
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
		
		// Create a filewriter for output
		setOutFilePointer();
		
		// Call the Compilation Engine
		
		
		
		
		
		
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
	 * Open an input file, throw error if no file given
	 */
	public FileReader setInFilePointer(String fin) {
		FileReader fr;
		
		if( !fin.endsWith(".jack") )
			System.out.println("ERROR: Input file must be of type .jack");
		else
			System.out.println("Input file is " + fin);
		
		
		try{
			fr = new FileReader(fin) ;
			return fr;
		}// end try
		catch (FileNotFoundException e){
			System.out.println("ERROR: " + fin + "not found");
		}//end catch
		return null;
	} // end public FileReader getFilePointer(String fin)
	
	
	
	
	
	
	
	
	
	
	/**
	 * Set the output file as specified in Chapter 10 of Nisan and Schoken 
	 */
	protected void setOutFilePointer(){
		FileWriter fw = null;
		try{
			fw = new FileWriter( getInFileName() + ".xml" );
		}
		catch(IOException e) {
			System.err.println(" Something wrong in setOutFilePointer");
			System.err.println(e);
		}
		
		this.outFile = fw;
	}// end protected void setOutFilePointer()

}// end class com.jackanalyzer

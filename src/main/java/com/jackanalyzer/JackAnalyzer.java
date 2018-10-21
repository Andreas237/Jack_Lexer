package com.jackanalyzer;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Arrays;
//Autogen functions
import lombok.Data;
import lombok.Getter;



/*!
 * This class runs the JackAnalyzer from Nisan and Schoken Chapter 10,
 * along with the requirements for PJ03. 
 */
@Data
public class JackAnalyzer extends MachineDescription {

	
	private String inFileName;					///< File name for writing
	@Getter private byte[] jackContent;			///< Content of JACK file
	@Getter private FileWriter outFile;			///< File to write output to
	
	
	
	
	
	
	
	
	
	
	///< Constructor
	/*!
	 * Run the JackAnanlyzer class on an input file
	 */
	public JackAnalyzer(String fin /**<Input filename to be passed from the command line>*/) {
		
		// Open the jack file for reading, error if incorrect file type
		System.out.println("Going to get Jack");
		this.jackContent = this.fileToBytes(fin);
		if(getJackContent() == null)
			System.out.println("Failed to read anything from " + fin);
		else
			System.out.println("read a file");
		
		
		
		
		setInFileName(fin.replace(".jack", ""));	///< set the inFileName
		
		
		removeExistingOutputFile();					///< Delete existing output file if it exists
		
		
		this.outFile = setOutFilePointer();			///< Create a filewriter for output
			
		
		
		// Call the Compilation Engine
		/*
		 * Can't use regex/substrings
		 * Instead use byte ops.
		 * Can I make a map of {(byte:char),(keyword)} and use same read
		 * and classify method as I did at CSUEB?
		 * FiniteAutomaton Sim -> first read, then determine states at each KW
		 */
		
		
		
		
		
		// Close the file pointers
		try {
			System.out.println("Trying to close the file");
			this.outFile.close();
		} // end try
		catch (IOException e) {
			System.out.println("Caught an error closing file pointers");
			e.printStackTrace();
		}// end catch (IOException e)
		
	}//end public JackAnalyzer() 
	
	
	
	
	
	
	
	
	
	
	/*!
	 * Given a filename:
	 * 	Error if the file doesn't exist.
	 * 	Read the file into the byte array.
	 * 	Close the file.
	 */
	public byte[] fileToBytes(String fin){
		
		File fr = null;
		byte [] inBytes = null;
		
		// Only files of type jack allowed
		if(!fin.endsWith(".jack")){
			System.out.println("ERROR: Input file must be of type .jack");
			System.exit(1);
		}
		else
			System.out.println("Input file is " + fin);
		
		// Read all the bytes from the jackfile into the array
		try{
			fr = new File(fin);
			inBytes = Files.readAllBytes(fr.toPath());
		}// end try
		catch (FileNotFoundException e){
			System.out.println("ERROR: " + fin + "not found");
			System.exit(1);
		}// end catch (FileNotFoundException e)
		catch(IOException e){
			System.out.println("ERROR: couldn't read all bytes from " + fin);
			e.printStackTrace();
			System.exit(1);
		}// end catch(IOException e)
		
		return inBytes;
	} // end public byte[] fileToBytes(String fin)
	
	
	
	
	
	
	
	
	
	
	/*!
	 * Delete output file if it exists.  Log to console if it 
	 */
	private void removeExistingOutputFile(){
		
		File f = new File(this.inFileName + ".xml");
		if(f.exists()){
			if(!f.delete())
				System.out.println("WARNING: Could not delete " + this.inFileName + ".xml");
		}// end if(f.exists())
		else
			System.out.println("WARNING: " + f.getName() + " DOES NOT exists.");
		
	}// end private void removeExistingOutputFile()
	
	
	
	
	
	
	
	
	
	
	/*!
	 * Set the output file as specified in Chapter 10 of Nisan and Schoken 
	 */
	protected FileWriter setOutFilePointer(){
		FileWriter fw = null;
		try{
			fw = new FileWriter(getInFileName() + ".xml");
			return fw;
		}
		catch(IOException e) {
			System.err.println(" Something wrong in setOutFilePointer");
			System.err.println(e);
		}
		
		return fw;
	}// end protected void setOutFilePointer()
	
	
	
	
	
	
	
	
	
	
	/*!
	 * Find the occurances of the token in the Jack File
	 * Does not check whether they are in a comment block or not
	 */
	public int tokenCount(byte[] tokenCheck /**<input byte[] to check against Jack byte[]>*/){
		
		int count = 0;
		int tokenCheckLength = tokenCheck.length;
				
		
		// Find the start of tokenCheck
		for(int i = 0; i < this.jackContent.length; i++){
			if(this.jackContent[i] == tokenCheck[0]){
				if(tokenWordCheck(tokenCheck, Arrays.copyOfRange(this.jackContent, i, i + tokenCheckLength)))
					count++;
			}// end if(this.jackContent[i] == tokenCheck[0])
		}// end for(int i = 0; i < this.jackContent.length; i++)
		
		
		return count;
		
	}// end public int tokenCount(byte[] tokenCheck)
	
	
	
	
	
	
	
	
	
	
	///< tokenWordCheck(byte[] tokenIn, byte[] arrayIn)
	/*!
	 * Check if the remaining tokenIn is contained in the arrayIn
	 */
	private boolean tokenWordCheck(byte[] tokenIn, byte[] arrayIn){
		
		// If tokenIn has no more characters then it has been fully checked
		if(tokenIn.length == 0)
			return true;
		
		// Compare whether the first characters match
		// If so compare the rest of the word, else false
		if(tokenIn[0] == arrayIn[0])
			return tokenWordCheck(Arrays.copyOfRange(tokenIn,1,tokenIn.length), 
								   Arrays.copyOfRange(arrayIn,1,tokenIn.length)); 
		else
			return false;
	}//end private boolean tokenWordCheck(byte[] tokenIn, byte[] arrayIn)
	
	

}//end class com.jackanalyzer

package com.jackanalyzer;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

///< CompilationEngine
/*!
 * Work with the files and machine
 */
@Data
public class CompilationEngine{

	
	ArrayList<File> jackFiles;		///< Jack input file names
	HashMap<File,File> logFiles;	///< Log output file names
	HashMap<File,File> tokFiles;	///< Token output file names
	ArrayList<String> inputStrings;	///< Read from file
	







	///< CompilationEngine
	/*!
	 * Get the input filenames and files.
	 * Extract the input strings.
	 * Simulate an NFA.
	 * Write the output files.
	 */
	public CompilationEngine(){
		jackFiles = new ArrayList<File>();
		logFiles = new HashMap<File,File>();
		tokFiles = new HashMap<File,File>();
		//TODO: setup filenames
		setFiles();
		checkJackFilesSize();
		
		//TODO: Call JackProcessor() to get input strings
		//TODO: Call Tokenizer()
		//TODO: Call logger()

		//TODO: parse file into inputStrings
		//TODO: 
	}// end public CompilationEngine()
	







	///< checkJackFilesSize()
	/*!
	 * Exit with error if no files were gathered for processing
	 */
	private void checkJackFilesSize(){
		if( jackFiles.size() == 0){
			System.out.println("ERROR: No .jack files found in the project path!");
			System.out.println("Add .jack files anywhere in the project directory to solve.");
				System.exit(1);
		}// end if( jackFiles.size() == 0)
	}// end void checkJackFilesSize()
	







	///< getAllFiles()
	/*!
	 * Get all the files in the directory, and filter to .jack files
	 * Commented out downward recursion
	 */
	private ArrayList<File> getAllJackFiles(File currentDir){
		ArrayList<File> fileList = new ArrayList<File>(); 
		File[] currentFiles = currentDir.listFiles();
        for(File f : currentFiles){
        	
            if(f.getName().endsWith(".jack") && !f.getName().contains("test"))
            	fileList.add(f);
	    	// No need to recurse down
	    	if(f.isDirectory()) 
	    		fileList.addAll(getAllJackFiles(f));
        }// end for(File f : currentFiles)
        
        return fileList;
	}// end void getAllFiles(File currentDir)
	







	///< setFiles()
	/*!
	 * Create files for .tok and .log corresponding to the input jackfiles
	 */
	private void setFiles(){
		
		File currentDir = new File("./");		///< Directory to search for .jackfiles
		File tempTok, tempLog;					// temp file

		// Get the jack files the project directory
		jackFiles = getAllJackFiles(currentDir);
		
		
		// For every .jack create a corresponding output file
		for (File jack : jackFiles){
			tempTok = new File("./results/" + jack.getName().replace(".jack", ".tok"));
			tokFiles.put(jack, tempTok);
			tempLog = new File("./results/" + jack.getName().replace(".jack", ".log"));
			logFiles.put(jack, tempLog);

		}// end for (File f : jackFiles)
		
	
	}// end void setFiles()
	
	

}// end public class CompilationEngine

package com.jackanalyzer;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

///< CompilationEngine
/*!
 * Work with the files and machine
 */
@Data
public class CompilationEngine{

	
	ArrayList<File> jackFiles;			///< Jack input file names
	ArrayList<File> logFiles;			///< Log output file names
	ArrayList<File> tokFiles;			///< Token output file names
	ArrayList<String> files;			///< String input file names
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
		logFiles = new ArrayList<File>();
		tokFiles = new ArrayList<File>();
		//TODO: setup filenames
		setFiles();
		//TODO: setup input/output file locations
		//TODO: .tok and .jack extensions
		//TODO: create the filename
		//TODO: parse file into inputStrings
		//TODO: 
	}// end public CompilationEngine()
	







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
		File currentDir = new File("./");
		jackFiles = getAllJackFiles(currentDir);
		
		File temp;
		for (File f : jackFiles){
			temp = new File("./results/" + f.getName().replace(".jack", ".tok"));
			tokFiles.add(temp);
			temp = new File("./results/" + f.getName().replace(".jack", ".log"));
			logFiles.add(temp);
		}// end for (File f : jackFiles)
		
	}// end void setFiles()
	
	

}// end public class CompilationEngine

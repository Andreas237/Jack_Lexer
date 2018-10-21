package com.jackanalyzer;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;

///< CompilationEngine
/*!
 * Work with the files and machine
 */
@Data
public class CompilationEngine{

	
	ArrayList<String> files;		///< String file names
	ArrayList<String> inputStrings;	///< Read from file
	







	///< CompilationEngine
	/*!
	 * Get the input filenames and files.
	 * Extract the input strings.
	 * Simulate an NFA.
	 * Write the output files.
	 */
	public CompilationEngine(){
		//TODO: setup filenames
		setFiles();
		//TODO: setup input/output file locations
		//TODO: .tok and .jack extensions
		//TODO: create the filename
		//TODO: parse file into inputStrings
		//TODO: 
	}// end public CompilationEngine()
	







	///< getAllFiles()
	private File[] getAllFiles(File currentDir){
		//TODO: File[] fileList should be an arrayList and in the case of directory append files
		File[] fileList = currentDir.listFiles();
        for(File f : fileList){
            if(f.isDirectory()) 
                getAllFiles(f);
            if(f.isFile())
                System.out.println(f.getName());
        }// end for(File f : filesList)
        return fileList;
	}// end void getAllFiles(File currentDir)
	







	///< setFiles()
	/*!
	 * Add the file names
	 */
	private void setFiles(){
		File currentDir = new File("./JackFiles/");
		File[] jackFiles;
		getAllFiles(currentDir);
		
	}// end void setFiles()
	
	

}// end public class CompilationEngine

package JackAnalyzer_Test;

import com.jackanalyzer.*;

// import org.junit.BeforeClass;
// import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;




/**
 * 
 * @author andreasslovacek
 * @category Test Class
 */
public class JackAnalyzer_Test {
	
	
	static JackAnalyzer tester;
	static Path testFile;
	static String testFileStr;
	
	@BeforeAll
	public static void testSetup() {
		// tester = new JackAnalyzer();
		testFile = FileSystems.getDefault().getPath("../../../../.", "test.jack");
		// testFileStr = testFile.toString() ;
		testFileStr = "empty.jack";
		//testFile = "/Users/andreasslovacek/OneDrive/Classes, Books, & Learning/UCCS/CS5700_Automatat.Complexity.Computability/PJ03/test.jack";
	} // end public static void testSetup()
	
	
	// Setup temporary files for test
	
	
	// Unit test for opening the file passed from CLI
	/*
	 * The file is opened, and the pointer returned.
	 * Test Asserts that the file pointer is was returned and is open
	 */
	@Test
	public void testSetFilePointer(){  assertNotNull( tester.setInFilePointer(testFileStr) ) ; }

}// end public class JackAnalyzer_Test 

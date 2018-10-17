package com.jackanalyzer;





import lombok.Data;
import lombok.Getter;
import java.util.HashMap;
import java.util.Set;



/**
 * @author andreasslovacek
 * This class generates the byte byterings for every keyword
 */
@Data
class Keywords {
	
	@Getter private HashMap Categories;
	@Getter private Set		Discards;
	
	
	// Setup the hashmap of <BytePatter, KeywordName>  
	Keywords(){
		
		Categories = new HashMap<Byte[],String>();
		Byte[] byter ;
		
		
		
		//==== KW_CONST ==== 
		
		//	true
		byter = new Byte[] 	{'t','r','u','e'};
		Categories.put(byter, "KW_CONST");
		
		// false
		byter = new Byte[] 	{'f','a','l','s','e'};
		Categories.put(byter, "KW_CONST");
		
		// null
		byter = new Byte[] 	{'n','u','l','l'};
		Categories.put(byter, "KW_CONST");
		
		// this
		byter = new Byte[] 	{'t','h','i','s'};
		Categories.put(byter, "KW_CONST");
		
		
		
		//==== KW_TYPE ==== 
		
		//int
		byter 	= new Byte[] 	{'i','n','t'};
		Categories.put(byter, "KW_TYPE");

		
		//char
		byter 		= new Byte[] 	{'c','h','a','r'};
		Categories.put(byter, "KW_TYPE");
		
		//boolean
		byter 		= new Byte[] 	{'b','o','o','l','e','a','n'};
		Categories.put(byter, "KW_TYPE");
		
		
		
		//==== KW_VARDEC ==== 
		//static
		byter 		= new Byte[] 	{'s','t','a','t','i','c'};
		Categories.put(byter, "KW_VARDEC");
		
		//static
		byter 		= new Byte[] 	{'f','i','e','l','d'};
		Categories.put(byter, "KW_VARDEC");
		
		
		
		//==== KW_SUBDEC ==== 
		//conbyteructor
		byter 		= new Byte[] 	{'c','o','n','s','t','r','u','c','t','o','r'};
		Categories.put(byter, "KW_SUBDEC");
		
		//function
		byter 		= new Byte[] 	{'f','u','n','c','t','i','o','n'};
		Categories.put(byter, "KW_SUBDEC");
		
		//method
		byter 		= new Byte[] 	{'m','e','t','h','o','d'};
		Categories.put(byter, "KW_SUBDEC");
		
		
		
		
		//==== KW_VAR ==== 
		byter 		= new Byte[] 	{'v','a','r'};
		Categories.put(byter, "KW_VAR");

		
		
		//==== KW_VOID ==== 
		byter 		= new Byte[] 	{'v','o','i','d'};
		Categories.put(byter, "KW_VAR");

		
		
		//==== KW_CLASS ==== 
		byter 		= new Byte[] 	{'c','l','a','s','s'};
		Categories.put(byter, "KW_CLASS");
		
		
		
		//==== KW_LET ==== 
		byter 		= new Byte[] 	{'l','e','t'};
		Categories.put(byter, "KW_LET");

		
		
		//==== KW_IF ==== 
		byter 		= new Byte[] 	{'i','f'};
		Categories.put(byter, "KW_IF");
		
		
		
		//==== KW_ELSE ==== 
		byter 		= new Byte[] 	{'e','l','s','e'};
		Categories.put(byter, "KW_ELSE");
		
		
		
		//==== KW_WHILE ==== 
		byter 		= new Byte[] 	{'w','h','i','l','e'};
		Categories.put(byter, "KW_WHILE");
		
		
		
		//==== KW_DO ==== 
		byter 		= new Byte[] 	{'d','o'};
		Categories.put(byter, "KW_DO");
		
		
		
		//==== KW_RETURN ==== 
		byter 		= new Byte[] 	{'r','e','t','u','r','n'};
		Categories.put(byter, "KW_RETURN");
		
		
		
		//==== SY_LPAREN ==== 
		byter 		= new Byte[] 	{'('};
		Categories.put(byter, "SY_LPAREN");
		
		
		
		//==== SY_RPAREN ==== 
		byter 		= new Byte[] 	{')'};
		Categories.put(byter, "SY_RPAREN");
		
		
		
		//==== SY_LBRACKET ==== 
		byter 		= new Byte[] 	{'['};
		Categories.put(byter, "SY_LBRACKET");
		
		
		
		//==== SY_RBRACKET ==== 
		byter 		= new Byte[] 	{']'};
		Categories.put(byter, "SY_RBRACKET");
			
		
		
		//==== SY_LBRACE ==== 
		byter 		= new Byte[] 	{'{'};
		Categories.put(byter, "SY_LBRACE");
		
		
		
		//==== SY_RBRACE ==== 
		byter 		= new Byte[] 	{'}'};
		Categories.put(byter, "SY_RBRACE");
		
		
		
		//==== SY_SEMI ==== 
		byter 		= new Byte[] 	{';'};
		Categories.put(byter, "SY_SEMI");
		
		
		
		//==== SY_PERIOD ==== 
		byter 		= new Byte[] 	{'.'};
		Categories.put(byter, "SY_PERIOD");
		
		
		
		//==== SY_COMMA ==== 
		byter 		= new Byte[] 	{','};
		Categories.put(byter, "SY_COMMA");
		
		
		
		//==== SY_EQ ==== 
		byter 		= new Byte[] 	{'='};
		Categories.put(byter, "SY_EQ");
		
		
		
		//==== SY_MINUS ==== 
		byter 		= new Byte[] 	{'-'};
		Categories.put(byter, "SY_MINUS");
		
		
		
		//==== SY_NOT ==== 
		byter 		= new Byte[] 	{'~'};
		Categories.put(byter, "SY_NOT");
		
		
		
		//==== SY_OP ==== 
		byter 		= new Byte[] 	{'<','>','|','&','/', '*','+'};
		Categories.put(byter, "SY_OP");
		
		
		//============================================================
		//				Discards
		//============================================================
		
		//==== EOL ====
		/*
		byter 		= new Byte[] 	{'\n','\r'};
		Discards.add(byter);
		*/
		
		
		//==== COMMENT_EOL ====
		/*
		byter 		= new Byte[] 	{'/'+'/'};
		Discards.add(byter);
		*/
		
		
		//==== COMMENT_BLK ====
		/*
		byter 		= new Byte[] 	{'/'+'*','*'+'/'};
		Discards.add(byter);
		*/
		

	}// end Keywords() 

}// end class Keywords

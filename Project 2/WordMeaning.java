/********************************
        CLASS DESCRIPTION
Creates a "value" object that holds
words and their meanings. These are
input into individual nodes in the
binary search tree.
********************************/

package app;

public class WordMeaning {
    
    String word, meaning; 
	
	public WordMeaning (String w, String m) { 
		word = w; 
		meaning = m;  
	}
	
	String getWord() {
            return word;
	}
	
	String getMeaning() {
            return meaning;
	}
} // end class

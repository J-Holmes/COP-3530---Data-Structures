/********************************
        CLASS DESCRIPTION
Creates nodes to make up the binary
search tree. Each holds a root value,
and left/right nodes for the 
child nodes.
********************************/

package app;

    public class WordMeaningNode {

	WordMeaning value; 
	WordMeaningNode left, right; 
	
        
	public WordMeaningNode(WordMeaning value) { 
		this.value = value; 
		left = null; 
		right = null; 
	}
        
        
}

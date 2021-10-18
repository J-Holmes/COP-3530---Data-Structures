/********************************
        CLASS DESCRIPTION
This class holds the binary search
tree object and its corresponding 
methods.
********************************/

package app;

import java.util.ArrayList;

public class WordList {

    String s;
    
    public WordList() { 
        s = " ";
    }
    
    // this was part of the assigment zip files  
    public void insert(WordMeaningNode node, WordMeaning value) {
        if (value.getWord().compareTo(node.value.getWord()) < 0) { 
            if (node.left != null) { 
                insert(node.left, value); 
            } 
            else { 
                s += node.value.getWord() + "  "; 
		//System.out.println(" Inserted [" + value.getWord() + "] to left of [" + node.value.getWord()  + "]");
		node.left = new WordMeaningNode(value); 
            } 
	} 
	else if (value.getWord().compareTo(node.value.getWord())  > 0) {
            if (node.right != null){
                insert(node.right, value);
            } 
            else {
                s += node.value.getWord() + "  "; 
		//System.out.println(" Inserted [" + value.getWord() + "] to right of [" + node.value.getWord() + "]");
		node.right = new WordMeaningNode(value);
            }
	}
    } // end insert
    
    // code included in zip
    public void inOrder(WordMeaningNode node){ // Ascending order
        if (node != null) {
            inOrder(node.left);
            System.out.print("\n " + node.value.getWord() + node.value.getMeaning());
            inOrder(node.right);
        }
    }
     
    public boolean contains(WordMeaningNode node, String key){
	// used to simplify comparsion operations below
        //int compare = key.compareTo(node.value.getWord());
        
        // if node is empty/null
        // not really needed since we initialized the tree with
        // hard coded values
        if (node == null ){
            return false;
        }
  
        else {
            int compare = key.compareTo(node.value.getWord());
                if (compare == 0){
                    return true;
                } 
                // recursive to check left & right
                else if ( compare < 0){ 
                    return contains(node.left,key);
                } 
                else {
                    return contains(node.right,key);
                }
        }    
    }     
     
    public WordMeaningNode deleteNode(WordMeaningNode node, String key){
	
        // String comparison for user entered key vs. node value
        int compare = key.compareTo(node.value.getWord());
        
        // node not found
        if (node == null ){
            return node;  
        }
            // recursive function to traverse tree
            if (compare < 0) {
                node.left = deleteNode(node.left,key);
            }
            else if (compare > 0) {
                node.right = deleteNode(node.right,key);
            }

            else {
                
                // for node with zero or one child
                if (node.left == null) {
                return node.right;
                }
                else if (node.right == null) {
                return node.left;
                }
                
            //nodes with 2 children - gets the child node that is
            // the next in order successor and resets the value
            node.value = getMinimumKey(node.right).value;
            
            // deletes the successor after assigning it to the parent value 
            node.right = deleteNode(node.right, node.value.getWord());
            }
        return node;
    }
    
    // helper function to find minimum value node in the subtree rooted at `curr`
    public WordMeaningNode getMinimumKey(WordMeaningNode curr){
        while (curr.left != null){ 
            curr = curr.left;
	}
	return curr;
    }

    /* finds the specific node based String key input
       this is for updating a word meaning at a later time */
    public WordMeaningNode findNode(WordMeaningNode node, String key){
        int compare = key.compareTo(node.value.getWord());
            
            if (node==null || node.value.meaning == key)
            return node;
            
            // recursive function to traverse tree
            if (compare < 0) {
                return findNode(node.left,key);
            }

            else if (compare > 0) {
                return findNode(node.right,key);
            }
        return node;
    }
    
    // this was part of the assigment zip files
    public String toString(){
        return s;
    }
} // end class
    

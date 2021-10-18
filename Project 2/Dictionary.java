/********************************
        PROGRAM DESCRIPTION
Data Structures Assignment # 2.      
This program will accept words and
their meanings and display the list
of words in alphabetical order.
For demonstration purposes, sample 
words and meanings have been hard
coded to load at runtime, but the user
has the ability to add new words
and meanings, as well as display
the list and deleted words.
********************************/

package app; 
import java.util.*;

class Dictionary {
    
public static void main(String[] args) {
    WordList tree = new WordList();
    WordMeaningNode root = new WordMeaningNode(new WordMeaning("",""));
    
    // list to hold values of words deleted from the bst
    ArrayList <String> deleted = new ArrayList<>();
    
    // initializing tree with hard coded list
    tree.insert(root, new WordMeaning("Cup"," - a container from which we drink"));
    tree.insert(root, new WordMeaning("School"," - a place of learning\n" + "        - any group of fish"));
    tree.insert(root, new WordMeaning("Library"," - a collection of books"));
    
    // to get user input from menu
    Scanner scnr = new Scanner(System.in);
    
    System.out.println("Welcome to the Dictionary Program");
           
    boolean progRunning = true;
        while (progRunning) { // to reprint menu until user exits program
            switch(MenuSelction()) {
                case 1: // gets words and their meaning from user using Scanner
                    String newWord = getInput("Enter a new word");
                    String newMeaning = getInput("Enter a meaning");
                    tree.insert(root, new WordMeaning(newWord," - " + newMeaning));
                    break;
                case 2: // delets word from list
                    String delWord = getInput("Enter word to delete");
                        if (tree.contains(root, delWord)) {
                            System.out.println("\nword deleted");
                            root.value = tree.deleteNode(root,delWord).value;
                            deleted.add(delWord); 
                        } 
                        else {
                            System.out.print("\nWord not in list");
                        }
                    break;
                case 3: // displays all current words & meanings
                    System.out.println("\nDisplaying current list");
                    tree.inOrder(root);
                    break;
                case 4: // displays list of deleted words
                    System.out.println("\nDisplaying deleted itmes");
                        for (String s : deleted) {
                            System.out.println(s);
                        }
                    break;
                case 5: // option to add additional meaning to word
                    String word = getInput("Enter word to add meaning");
                        if (tree.contains(root, word)) {
                            String addMeaning = getInput("Enter meaning to add");
                                System.out.println("\nMeaning added to word");
                                tree.findNode(root,word).value.meaning += ("\n     - " + addMeaning);
                            }
                            else {   
                                System.out.println("\nWord not found");
                            }
                    break;
                default : // instead of case 6 so user can press any other key to exit
                    System.out.println("Goodbye");
                    progRunning = false;
                    break; 
            } // end switch
        } // end while
} // end main

    // prints menu options in main and scans for user input
    private static int MenuSelction () {
        Scanner scan = new Scanner(System.in);
            System.out.println("\n\nPlease select an option from the list below:");
            System.out.println("1: Enter new word and meaning");
            System.out.println("2: Delete word from list");
            System.out.println("3: Display current list");
            System.out.println("4: Display deleted itmes");
            System.out.println("5: Add meaning to word");
            System.out.println("6: Exit");
	int selection = scan.nextInt();
	return selection;
    } // end Menu Selection

    private static String getInput(String str) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        System.out.println(str);
        input = scan.nextLine();
        return input;
    }
  
} // end class

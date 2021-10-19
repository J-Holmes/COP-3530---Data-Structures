package app;

import java.util.*;
import java.lang.*; 

public class Arithmetic implements Constants {

    //class variables
    Stack <Object> stack; 
    String expression;
    String postFixExpression;
    int length;
        
    // Constructor for Arithmetic object
    Arithmetic(String expression) {
        stack = new Stack <>();
        this.expression = expression;
        length = expression.length();
    }  
    
    // determines if the parentheses are balanced
    boolean isBalance() {
        
    int index = 0;
    boolean fail = false;
        
        try {
            while (index < length && !fail)	{
                char c = expression.charAt(index);
                
                switch (c) {
                    // If the character is a left parenthesis '(', 
                    // push it on to the stack
                    case '(':
                        stack.push(c);
                        break;
                    // if the character is a right parenthesis, ')', 
                    // visit the stack and pop the top element from off the stack.
                    case ')':
                        stack.pop();
                        break;
                    default:
                        break;
                }//end switch
            index++;
            }//end of while
	}//end of try
	
        catch (EmptyStackException e) {
            // prints out exceptions
            // System.out.println(e.toString()); 
            fail = true;
	}
        //If the stack contains any element at the end of reading the 
        // arithmetic expression, then the expression was not properly formed	
        return (stack.empty() && !fail);
    } // end isBalance

    
String postFixExpression() {
// converts the infix expression to postfix expression

        // A string to contain the postfix expression  
        // the output from this conversion.
        String postFix = "";
        Scanner scnr = new Scanner(expression);
        char current;
        boolean fail = false;
        
            while (scnr.hasNext() && !fail) {
                String token = scnr.next();
                
                //If the symbol is an operand (a number), 
                //write it to the output string.
                if (isNumber(token)) {
                    postFix = postFix + token + Constants.A_SPACE;
                } // end if is number
                
                else {
                    current = token.charAt(0);
                    // If the symbol is '(', push on to the stack,
                    if (current == Constants.LEFT_NORMAL) {
                        stack.push(current);
                    } // end if (
                    
                    // If the symbol is ')'
                    else if (current == Constants.RIGHT_NORMAL) {
                        try {
                            Character topmost = (Character)stack.pop();
                            char top = Character.valueOf(topmost);
                            while (top != Constants.LEFT_NORMAL) {
                                // Pop everything from the operator stack 
                                // down to the first '('. 
                                postFix = postFix + top + Constants.A_SPACE;
                                // Do not write the item ')'. Discard it.
                                top = (Character)stack.pop();
                            } // end while (
                        } // end try
                        catch (EmptyStackException e) {
                            fail = true;
                        } // end catch
                    } // end else if )
                
                //If the symbol scanned is an arithmetic operator, 
                // check for the following and apply accordingly    
                else if (isOperator(current)) {
                    try {
                        char top = (Character) stack.peek();
                        boolean higher = hasHigherPrecedence(top, current);
                            
                            /*If the operator on the top of the stack has higher 
                            or equal precedence, that operator is popped from 
                            off the stack, and is written to the to the 
                            output string. This process is continues until 
                            one of two things happen: */
                            
                            /*Either the first '(' is encountered.
                            The operator on the stack has lower precedence 
                            than the one just scanned */
                            while (top != Constants.LEFT_NORMAL && higher) {
                                postFix = postFix + stack.pop() + Constants.A_SPACE;
                                top = (Character) stack.peek();
		            } // end while
                            
                        stack.push(current);
                    } // end try
                    
                    catch (EmptyStackException e) {
                        stack.push(current);
                    } // end catch
                } // end else is is operator    
                } // end else
            } // end while
                try {
                    while (!stack.empty())   
                    postFix = postFix + stack.pop() + Constants.A_SPACE;
                }
                catch (EmptyStackException e) {
                    e.printStackTrace();
                }
    
    postFixExpression = postFix;
    return postFix;
    } // end post Fix


String evaluateRPN () {
    // Initialize an empty stack to hold operands only.
    Stack<String> stck = new Stack<>();
    Scanner scan = new Scanner(postFixExpression);
    Integer t1, t2;
             // While there are more symbols in the postfix string 
            while (scan.hasNext()) {
                String token = scan.next();
                
                //If the token is an operand, push it onto the stack.
                if (isNumber(token)) {
                    stck.push(token);
                } // end if is number
                
                // If the token is an operator
                else if (token.equals("+")) {
                    /* Pop the two topmost values from the stack, 
                    and store them in the order t1, the topmost, 
                    and t2 the second value.
                    Calculate the partial result in the following 
                    order t2 operator t1
                    Push this partial result on the stack. */
                    t1 = Integer.parseInt(stck.pop());
                    t2 = Integer.parseInt(stck.pop());
                    stck.push("" + (t1+t2));
                } // end else if

                else if (token.equals("-")) {
                    t1 = Integer.parseInt(stck.pop());
                    t2 = Integer.parseInt(stck.pop());
                    stck.push("" + (t2-t1));
                } // end else if

                else if (token.equals("*")) {
                    t1 = Integer.parseInt(stck.pop());
                    t2 = Integer.parseInt(stck.pop());
                    stck.push("" + (t1*t2));
                } // end else if

                else if (token.equals("/")) {
                    t1 = Integer.parseInt(stck.pop());
                    t2 = Integer.parseInt(stck.pop());
                    stck.push("" + (t1/t2));
                } // end else if
            } // end while
    // When the end of the input string is encountered, 
    // the result of the expression is popped from the stack. 
    
//    if (stck.empty() || (stck.size()>1)) {
//        return String.valueOf(answer) ;
//    }
//    
//    else { return stck.pop(); }
    
    
    return stck.pop();
    
} // end evaluate RPN

boolean isNumber (String s) {
    boolean number = true;
        try {
            Integer.parseInt(s);
        }
        catch(NumberFormatException e) {
            number = false;
        }
    return number;
} // end is Number method

boolean isOperator(char ch) {
    boolean operator;
        switch(ch) {
            case Constants.MULTIPLICATION:
            case Constants.DIVISION:
            case Constants.ADDITION:
            case Constants.SUBTRACTION:	
                operator = true;
                break;
            default:
                operator = false;
                break;	
        }
    return operator;	
} // end is operator

boolean hasHigherPrecedence(char top, char current) {
    boolean higher = true;
        switch(top) {
            case Constants.MULTIPLICATION:
            case Constants.DIVISION: 
                switch(current){
                    case Constants.ADDITION:
                    case Constants.SUBTRACTION:
//                    case Constants.MULTIPLICATION:
//                    case Constants.DIVISION:    
                        higher = true;
                        break;
                }
            case Constants.ADDITION:
            case Constants.SUBTRACTION: 
                switch(current){
                    case Constants.ADDITION:
                    case Constants.SUBTRACTION:
                        higher = true;
                        break;
                    case Constants.MULTIPLICATION:
                    case Constants.DIVISION:
                        higher = false;
                        break;
                }
                               
                default:
                //higher = true;                         
                break;
        }
return higher;	
} // end has higher precedence


} // end class


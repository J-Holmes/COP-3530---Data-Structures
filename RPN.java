package app;

public class RPN {
    
public static void main(String[] arg) {
    
    String s[] =    {"5 + ) * ( 2",
                    "2 + ( -3 * 5 )",
                    "( ( 2 + 3 ) * 5 ) * 8",
                    "5 * 10 + ( 15 - 20 ) ) - 25",
                    " 5 + ( 5 * 10 + ( 15 - 20 ) - 25 ) * 9"                    
                    };
         
        for (int i = 0; i < s.length; i++) {
            Arithmetic a = new Arithmetic(s[i]);
                System.out.println("***Equation # " + (i+1) + "***");	
            if (a.isBalance()) {
                System.out.println("Expression " + s[i] + " is balanced\n");
                    a.postFixExpression();
                    System.out.println("The post fixed expression is " + a.postFixExpression());
                    System.out.println("");
                    System.out.println("The expression evaluates to " + a.evaluateRPN());
                    System.out.println("");                    
            } // end if
        
            else {
                System.out.println("Expression " + s[i] + " is not balanced\n");
            } // end else
            
        } // end for

} // end main
    
}

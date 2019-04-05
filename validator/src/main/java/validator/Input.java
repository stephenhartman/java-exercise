package validator;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Hello world!
 */
public class Input {

    private static final char START = '(';
    private static final char END = ')';
    String inputString;

    public Input(String s) {
        inputString = s;
    }

    public boolean process() {
        Character[] charObjectArray = inputString.chars().mapToObj(c -> (char)c).toArray(Character[]::new); 
        Stack<Character> inputStack = new Stack<Character>();
        for (int i = 0; i < inputString.length(); i++) {
            try {
                if (charObjectArray[i] == START)
                    inputStack.push(inputString.charAt(i));
                else if (charObjectArray[i] == END)
                    inputStack.pop();
            } catch (EmptyStackException ese) {
                return false;
            } catch (Exception e) {
                return false;
            }
        }
        if (inputStack.empty() == false)
            return false;
        return true;
    }
}

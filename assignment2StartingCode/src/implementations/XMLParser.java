package implementations;

public class XMLParser {
	
    private MyStack<String> stack; // Opening tags
    private MyQueue<String> errorQueue; // Errors
    private MyQueue<String> extrasQueue; // Unmatched closing tags

    // Constructor
    public XMLParser() {
        stack = new MyStack<>();
        errorQueue = new MyQueue<>();
        extrasQueue = new MyQueue<>();
    }

    
}

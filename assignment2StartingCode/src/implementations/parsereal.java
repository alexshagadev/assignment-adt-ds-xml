package implementations;
import java.io.File;
import java.util.Scanner;

public class parsereal {

    private MyStack<String> tagStack = new MyStack<>();
    private MyQueue<String> errorQueue = new MyQueue<>();
    private MyQueue<String> extrasQueue = new MyQueue<>();

    public void parse(String filePath) {
    	StringBuilder xmlContent  = new StringBuilder();
    	try {
    		Scanner xml = new Scanner(new File(filePath));
    		while (xml.hasNextLine()) {
    			xmlContent.append(xml.nextLine());
    		}
    		xml.close();
    	}
    	catch (Exception e) {
    		System.out.println("Error reading file: " + e.getMessage());
    	}
    		
        String[] lines = xmlContent.toString().split("\n");
        int lineNumber = 1;
        for (String line : lines) {
            String trimmedLine = line.trim();
            if (trimmedLine.isEmpty()) {
            	lineNumber++;
                continue;
            }

            // Process the line
            processLine(trimmedLine, lineNumber);
            lineNumber++;

        }
        handleFinalErrors();
    }

    private void processLine(String line, int lineNumber) {
        int length = line.length();
        StringBuilder currentTag = new StringBuilder();
        boolean insideTag = false;

        for (int i = 0; i < length; i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '<') {
                insideTag = true;
                currentTag.setLength(0);
            }
            else if (currentChar == '>') {
            	if (insideTag) {
                    insideTag = false;
                    String tagContent = currentTag.toString().trim();

                    if (tagContent.startsWith("/")) {
                        String tagName = tagContent.substring(1).trim();
                        handleEndTag(tagName, lineNumber);
                    } else if (tagContent.endsWith("/")) {
                    	continue;
                    } else {
                        String tagName = tagContent.split(" ")[0].trim();
                        tagStack.push(tagName);
                    }
                }
            } else if (insideTag) {
                currentTag.append(currentChar);
            }
        }
    }

    private void handleEndTag(String tagName, int lineNumber) {
        if (!tagStack.isEmpty() && tagStack.peek().equals(tagName)) {
            tagStack.pop();
        } else if (!tagStack.isEmpty() && tagStack.contains(tagName)) {
            handleMismatch(tagName, lineNumber);
        } else if (tagStack.isEmpty()) {
            errorQueue.enqueue("Line " + lineNumber + ": Error: Unmatched closing tag <" + tagName + ">");
        } else {
            extrasQueue.enqueue("Line " + lineNumber + ": Unmatched tag: <" + tagName + ">");
        }
    }

    private void handleMismatch(String tagName, int lineNumber) {
        while (!tagStack.isEmpty() && !tagStack.peek().equals(tagName)) {
            String unmatchedTag = tagStack.pop();
            errorQueue.enqueue("Error at line " + lineNumber + ":\nUnexpected tag <" + unmatchedTag);
        }
        if (!tagStack.isEmpty()) {
            tagStack.pop();
            System.out.println("Error at line " + lineNumber + ":\nEnd tag matches after mismatch: <" + tagName + ">");
        }
    }

    private void handleFinalErrors() {
        while (!tagStack.isEmpty()) {
            String unmatchedTag = tagStack.pop();
            errorQueue.enqueue("Error: Unclosed tag <" + unmatchedTag + ">");
        }

        if (errorQueue.isEmpty() && !extrasQueue.isEmpty()) {
            System.out.println("Error: Extra unmatched tags in extras queue.");
            while (!extrasQueue.isEmpty()) {
                String tag = extrasQueue.dequeue();
                System.out.println(tag);
            }
        } else if (!errorQueue.isEmpty() && extrasQueue.isEmpty()) {
            System.out.println("Error: Missing matching tags.");
            while (!errorQueue.isEmpty()) {
                String tag = errorQueue.dequeue();
                System.out.println(tag);
            }
        } else {
            while (!errorQueue.isEmpty() && !extrasQueue.isEmpty()) {
                String errorTag = errorQueue.dequeue();
                String extraTag = extrasQueue.dequeue();
                if (!errorTag.equals(extraTag)) {
                    System.out.println(errorTag + "> does not match " + extraTag);
                }
            }
        }
    }
    
    public static void main(String[] args) {

        String sample1Path = "./res/sample1.xml";
        String sample2Path = "./res/sample2.xml";

        parsereal parser = new parsereal();
        System.out.println("Sample1 Parse:");
        parser.parse(sample1Path);
        System.out.println("\n" + "Sample2 Parse:");
        parser.parse(sample2Path);
    }
}


package implementations;
import java.io.File;
import java.util.Scanner;

public class parsereal {

    private MyStack<String> tagStack = new MyStack<>();
    private MyQueue<String> errorQ = new MyQueue<>();
    private MyQueue<String> extrasQ = new MyQueue<>();

    public void parse(String filePath) {
    	//Using StringBuilder() and Scanner to get the XML Content
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
    		
    	//Each line is put into an Array, then processed
        String[] lines = xmlContent.toString().split("\n");
        int lineNumber = 1;
        for (String line : lines) {
            String trimmedLine = line.trim();
            if (trimmedLine.isEmpty()) {
            	lineNumber++;
                continue;
            }
            processLine(trimmedLine, lineNumber);
            lineNumber++;
        }
        handleOtherErrors();
    }

    private void processLine(String line, int lineNumber) {
        int length = line.length();
        StringBuilder currentTag = new StringBuilder();
        boolean insideTag = false;
        
        //Each character is read, then put into a String called currentTag.
        //currentTag is everything between < and >
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
                    	//Self closing tab is ignored.
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
    	//tagStack is checked and mismatches and mistakes are identified
        if (!tagStack.isEmpty() && tagStack.peek().equals(tagName)) {
            tagStack.pop();
        }
        else if (!tagStack.isEmpty() && tagStack.contains(tagName)) {
            handleMismatch(tagName, lineNumber);
        }
        else if (tagStack.isEmpty()) {
            errorQ.enqueue("Line " + lineNumber + ": Error: Unmatched closing tag <" + tagName + ">");
        }
        else {
            extrasQ.enqueue("Line " + lineNumber + ": Unmatched tag: <" + tagName + ">");
        }
    }

    private void handleMismatch(String tagName, int lineNumber) {
    	//the tagName is checked for a mismatch or the end tag is found for a previous tag.
        while (!tagStack.isEmpty() && !tagStack.peek().equals(tagName)) {
            String unmatchedTag = tagStack.pop();
            errorQ.enqueue("Error at line " + lineNumber + ":\nUnexpected tag <" + unmatchedTag);
        }
        if (!tagStack.isEmpty()) {
            tagStack.pop();
            System.out.println("Error at line " + lineNumber + ":\nEnd tag matches after mismatch: <" + tagName + ">");
        }
    }

    private void handleOtherErrors() {
        while (!tagStack.isEmpty()) {
            String unmatchedTag = tagStack.pop();
            errorQ.enqueue("Error: Unclosed tag <" + unmatchedTag + ">");
        }

        if (errorQ.isEmpty() && !extrasQ.isEmpty()) {
            System.out.println("Error: Extra unmatched tags in extras queue.");
            while (!extrasQ.isEmpty()) {
                String tag = extrasQ.dequeue();
                System.out.println(tag);
            }
        } else if (!errorQ.isEmpty() && extrasQ.isEmpty()) {
            System.out.println("Error: Missing matching tags.");
            while (!errorQ.isEmpty()) {
                String tag = errorQ.dequeue();
                System.out.println(tag);
            }
        } else {
            while (!errorQ.isEmpty() && !extrasQ.isEmpty()) {
                String errorTag = errorQ.dequeue();
                String extraTag = extrasQ.dequeue();
                if (!errorTag.equals(extraTag)) {
                    System.out.println(errorTag + "> does not match " + extraTag);
                }
            }
        }
    }
    
    public static void main(String[] args) {
    	//the sample XML file paths are passed to the parser.

        String sample1Path = "./res/sample1.xml";
        String sample2Path = "./res/sample2.xml";

        parsereal parser = new parsereal();
        System.out.println("Sample1 Parse:");
        parser.parse(sample1Path);
        System.out.println("\n" + "Sample2 Parse:");
        parser.parse(sample2Path);
    }
}


package implementations;
import java.io.File;
import java.util.Scanner;

//Starter Code.  Must ask about using java.util.Scanner and java.io.File.

public class XMLParser {
    private String[] tagStack;
    private int stackSize;
    private String[] errorQueue;
    private int errorQueueSize;
    private String[] extrasQueue;
    private int extrasQueueSize;

    public XMLParser() {
        tagStack = new String[100];
        stackSize = 0;
        errorQueue = new String[100];
        errorQueueSize = 0;
        extrasQueue = new String[100];
        extrasQueueSize = 0;
    }

    // Method to push onto stack
    private void pushToStack(String tag) {
        if (stackSize < tagStack.length) {
            tagStack[stackSize++] = tag;
        }
    }

    // Method to pop from stack
    private String popFromStack() {
        if (stackSize > 0) {
            return tagStack[--stackSize];
        }
        return null;
    }

    // Method to enqueue to error queue
    private void enqueueErrorQueue(String tag) {
        if (errorQueueSize < errorQueue.length) {
            errorQueue[errorQueueSize++] = tag;
        }
    }

    // Method to dequeue from error queue
    private String dequeueErrorQueue() {
        if (errorQueueSize > 0) {
            String tag = errorQueue[0];
            for (int i = 0; i < errorQueueSize - 1; i++) {
                errorQueue[i] = errorQueue[i + 1];
            }
            errorQueueSize--;
            return tag;
        }
        return null;
    }

    // Method to enqueue to extras queue
    private void enqueueExtrasQueue(String tag) {
        if (extrasQueueSize < extrasQueue.length) {
            extrasQueue[extrasQueueSize++] = tag;
        }
    }

    // Method to dequeue from extras queue
    private String dequeueExtrasQueue() {
        if (extrasQueueSize > 0) {
            String tag = extrasQueue[0];
            for (int i = 0; i < extrasQueueSize - 1; i++) {
                extrasQueue[i] = extrasQueue[i + 1];
            }
            extrasQueueSize--;
            return tag;
        }
        return null;
    }

    // Method to parse the XML content
    public void parseXML(String xml) {
        String[] lines = xml.split("\n");

        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) continue;

            // If it's a self-closing tag, ignore it
            if (line.startsWith("<") && line.endsWith("/>")) {
                continue;
            }

            // If it's a start tag
            if (line.startsWith("<") && line.endsWith(">") && !line.startsWith("</")) {
                pushToStack(line);
            }
            // If it's an end tag
            else if (line.startsWith("</") && line.endsWith(">")) {
                String endTag = line;

                // Check if it matches the start tag
                if (stackSize > 0 && tagStack[stackSize - 1].equals(endTag.replace("</", "<"))) {
                    popFromStack();  // Pop the matching start tag
                }
                // If it doesn't match, check the error queue
                else if (errorQueueSize > 0 && errorQueue[0].equals(endTag)) {
                    dequeueErrorQueue();  // Remove from the error queue and ignore
                }
                // If stack is empty, add to error queue
                else if (stackSize == 0) {
                    enqueueErrorQueue(endTag);
                }
                // Otherwise, search for matching start tag in stack
                else {
                    boolean matched = false;
                    while (stackSize > 0) {
                        String startTag = popFromStack();
                        if (startTag.equals(endTag.replace("</", "<"))) {
                            matched = true;
                            break;
                        } else {
                            enqueueErrorQueue(startTag);  // Add unmatched tags to errorQueue
                        }
                    }

                    // If no match was found, add this end tag to extrasQueue
                    if (!matched) {
                        enqueueExtrasQueue(endTag);
                    }
                }
            }
        }

        // Handle any remaining unmatched tags
        while (stackSize > 0) {
            enqueueErrorQueue(popFromStack());
        }

        // Reporting errors
        reportErrors();
    }

    // Method to report errors from the queues
    private void reportErrors() {
        while (errorQueueSize > 0 || extrasQueueSize > 0) {
            if (errorQueueSize == 0 && extrasQueueSize > 0) {
                System.out.println("Error: Unmatched tag in extras queue: " + dequeueExtrasQueue());
            } else if (errorQueueSize > 0 && extrasQueueSize == 0) {
                System.out.println("Error: Unmatched tag in error queue: " + dequeueErrorQueue());
            } else {
                String errorFromQueue = errorQueue[0];
                String extraFromQueue = extrasQueue[0];
                if (!errorFromQueue.equals(extraFromQueue)) {
                    System.out.println("Error: Mismatch found - errorQueue: " + dequeueErrorQueue());
                } else {
                    System.out.println("No Error: Tags match, removed from both queues: " + dequeueErrorQueue());
                    dequeueExtrasQueue();
                }
            }
        }
    }

    // Turning the XML file into a string
    private static String readFileAsString(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return content.toString();
    }

    //Trying to read the XML File
    public static void main(String[] args) {

        String xmlFilePath = "./res/sample1";

        String xmlContent = readFileAsString(xmlFilePath);

        // Parse the XML
        XMLParser parser = new XMLParser();
        parser.parseXML(xmlContent);
    }
}

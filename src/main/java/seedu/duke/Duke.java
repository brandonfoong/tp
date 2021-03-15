package seedu.duke;

import java.util.Scanner;

import seedu.duke.command.Command;

/**
 * Main class of the application, where the entry point is.
 */
public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcome();

        Scanner in = new Scanner(System.in);
        
        // Sample usage of parser
        Parser parser = new Parser(new Ui(), new Data());
        try {
            Command c = parser.parse(in.nextLine());
            c.execute();
        } catch (Exception e) {
            // TODO: A better exception handler should be implemented (after Ui is done)
            System.out.println(e);
        }
        
        in.close();
    }
}

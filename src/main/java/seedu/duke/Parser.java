package seedu.duke;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import seedu.duke.command.Command;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.exception.UnknownException;

/* Adapted from https://github.com/fsgmhoward/ip/blob/master/src/main/java/duke/Parser.java */
/**
 * This is the parser for parsing the line of command to tokens and construct the command class.
 */
public class Parser {
    /**
     * This is the delimiter for string join.
     * default value is a single whitespace (for string split, it is any number of whitespaces)
     */
    public static final String DELIMITER = " ";

    protected Ui ui;
    protected Data data;

    /**
     * This is the constructor of the Parser class.
     * @param ui Ui instance which will be passed to the command instances
     * @param data Data instance which will be passed to the command instances
     */
    public Parser(Ui ui, Data data) {
        this.ui = ui;
        this.data = data;
    }

    /**
     * Parse a line of command, put it into a hash map, then construct a command instance
     * Segments are splitted by '/'
     * Example: commandX some_description /optY Y_description Y_description_1 /optZ Z_description
     * Output argument hashmap:
     * |   key   |             value             |
     * |---------|-------------------------------|
     * | command | commandX                      |
     * | payload | some_description              |
     * | optY    | Y_description Y_description_1 |
     * | optZ    | Z_description                 |
     * Then, ui, tasks and this argument hashmap will be passed to initialize a command class.
     * The command class is determined by the 1st token of the command string. For example, for a command string 'find',
     * command class 'duke.command.FindCommand' will be initialized.
     * @param fullCommand The line of command to be parsed
     * @return A Command instance which is ready to be executed
     * @see Command
     */
    public Command parse(String fullCommand) throws InvalidInputException, UnknownException {
        HashMap<String, String> arguments = new HashMap<>();
        String[] tokens = fullCommand.split("\\s+");
        // If first token (command) is empty, there are empty spaces typed in at the front - so we remove it
        if (tokens[0].isEmpty()) {
            tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
        }
        if (tokens.length == 0) {
            throw new InvalidInputException(InvalidInputException.Type.EMPTY_STRING);
        }
        arguments.put("command", tokens[0]);

        // Default key is "payload"
        String key = "payload";
        ArrayList<String> values = new ArrayList<>();
        for (int i = 1; i < tokens.length; ++i) {
            // Check whether this token is a new key
            if (tokens[i].charAt(0) == '/') {
                // If it is, save current value into the map and start a new k-v pair
                arguments.put(key, String.join(DELIMITER, values));
                key = tokens[i].substring(1);
                values.clear();
            } else {
                // If not, append this token to the end of the value
                values.add(tokens[i]);
            }
        }

        // Store the last k-v pair
        // Store even when `values` is empty, as that indicates an empty string
        arguments.put(key, String.join(DELIMITER, values));

        // Initialize a respective class from the command (by capitalize first character)
        String className = tokens[0] + "Command";
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        className = Commons.COMMAND_CLASS_PREFIX + className;
        try {
            Class<?> cls = Class.forName(className);
            Constructor<?> constructor = cls.getDeclaredConstructor(Ui.class, Data.class, HashMap.class);
            Object obj = constructor.newInstance(ui, data, arguments);
            return (Command) obj;
        } catch (ClassNotFoundException e) {
            // *Command class cannot be found!
            throw new InvalidInputException(InvalidInputException.Type.UNKNOWN_COMMAND, e);
        } catch (Exception e) {
            // Some other weird error occurred here
            throw new UnknownException(e);
        }
    }
}

package de.christianbernstein.universe.command;

public class CommandException extends Exception {

    private final CommandResult result;

    public CommandException(final String message, final CommandResult commandResult){
        super(message);
        this.result = commandResult;
    }

    public CommandResult getResult() {
        return result;
    }
}

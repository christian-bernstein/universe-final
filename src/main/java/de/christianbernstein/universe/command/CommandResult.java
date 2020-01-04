package de.christianbernstein.universe.command;

import java.util.ArrayList;

public class CommandResult {

    private final ArrayList<Exception> exceptions;

    private boolean success;

    private boolean matchingReceiverType = false;

    public CommandResult setSuccess(final boolean success) {
        this.success = success;
        return this;
    }

    public CommandResult setMatchingReceiverType(final boolean matchingReceiverType) {
        this.matchingReceiverType = matchingReceiverType;
        return this;
    }

    public CommandResult appendException(final Exception ex) {
        this.exceptions.add(ex);
        return this;
    }

    ArrayList<Exception> getExceptions(){
        return this.exceptions;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean isMatchingReceiverType() {
        return this.matchingReceiverType;
    }

    {
        this.exceptions = new ArrayList<>();
        this.success = false;
    }
}

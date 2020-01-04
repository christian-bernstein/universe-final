package de.christianbernstein.universe.command;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class BaseCommand {

    private final String command;

    private final String description;

    private final List<String> aliases;

    private final ReceiverType receiverType;

    public BaseCommand(final String command, final String description, final ReceiverType receiverType, final String... aliases){
        this.command = command;
        this.description = description;
        this.receiverType = receiverType;
        this.aliases = Arrays.asList(aliases);
    }

    public boolean isAppropriate(final String command){
        return command.equals(this.command) || this.aliases.contains(command);
    }

    public boolean checkReceiverType(final CommandSender sender){
        return receiverType.isValid(sender);
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public ReceiverType getReceiverType() {
        return receiverType;
    }
}

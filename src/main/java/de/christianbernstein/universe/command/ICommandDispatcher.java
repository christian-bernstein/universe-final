package de.christianbernstein.universe.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface ICommandDispatcher extends CommandExecutor {

    CommandResult dispatchCommand(final CommandResult result, final CommandSender sender, final Command command, final String label, final String[] args) throws CommandException;

    default boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            return dispatchCommand(new CommandResult(), commandSender, command, s, strings).isSuccess();
        } catch (CommandException e) {
            //todo add handler
            e.printStackTrace();
            return false;
        }
    }

    CommandResult defaultHandling(final CommandResult result, final CommandSender sender, final Command command, final String label) throws CommandException;

    boolean registerHandler(final BaseCommand command, final ICommandHandler handler);

    boolean removeHandler(final String label);
}

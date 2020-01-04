package de.christianbernstein.universe.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ICommandHandler {

    CommandResult handleCommand(final CommandSender sender, final Command command, final String label, final String[] args) throws CommandException;

    default void handleException(final Exception ex){}
}

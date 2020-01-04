package de.christianbernstein.universe.command.commands;

import de.christianbernstein.universe.command.CommandException;
import de.christianbernstein.universe.command.CommandResult;
import de.christianbernstein.universe.command.ISeparatedCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DebugCommand implements ISeparatedCommandHandler {

    @Override
    public CommandResult handleAsPlayer(CommandResult result, CommandSender sender, Command command, String label, String[] args) throws CommandException {
        return result.setSuccess(true);
    }

    @Override
    public CommandResult handleAsConsole(CommandResult result, CommandSender sender, Command command, String label, String[] args) throws CommandException {

        return result.setSuccess(true);
    }
}

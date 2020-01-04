package de.christianbernstein.universe.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface ISeparatedCommandHandler extends ICommandHandler {

    @Override
    default CommandResult handleCommand(final CommandSender sender, final Command command, final String label, final String[] args) throws CommandException {
        if (sender instanceof Player) return this.handleAsPlayer(new CommandResult(), sender, command, label, args);
        else return this.handleAsConsole(new CommandResult(), sender, command, label, args);
    }

    CommandResult handleAsPlayer(final CommandResult result, final CommandSender sender, final Command command, final String label, final String[] args) throws CommandException ;

    CommandResult handleAsConsole(final CommandResult result, final CommandSender sender, final Command command, final String label, final String[] args) throws CommandException ;
}

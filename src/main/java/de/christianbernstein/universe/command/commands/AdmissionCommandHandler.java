package de.christianbernstein.universe.command.commands;

import de.christianbernstein.universe.command.CommandException;
import de.christianbernstein.universe.command.CommandResult;
import de.christianbernstein.universe.command.ISeparatedCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Date;

public class AdmissionCommandHandler implements ISeparatedCommandHandler {

    @Override
    public CommandResult handleAsPlayer(CommandResult result, CommandSender sender, Command command, String label, String[] args) throws CommandException {
        if (args.length == 1){
            sender.sendMessage(new Date().toLocaleString());
        }else if (args.length == 2){
            if (args[2].equals("list")){

            }
        }

        return result;
    }

    @Override
    public CommandResult handleAsConsole(CommandResult result, CommandSender sender, Command command, String label, String[] args) throws CommandException {
        return null;
    }
}

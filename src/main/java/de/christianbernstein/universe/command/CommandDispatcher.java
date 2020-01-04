package de.christianbernstein.universe.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandDispatcher implements ICommandDispatcher {

    private final HashMap<BaseCommand, ICommandHandler> register;

    public CommandDispatcher(){
        this.initiate();
    }

    //called from the constructor, to register new handlers
    public void initiate() {}

    // handle incoming player, console commands
    //   - separates player & console commands
    @Override
    public CommandResult dispatchCommand(CommandResult result, CommandSender sender, Command command, String label, @NotNull String[] args) throws CommandException {
        //if no arguments there, call the default handler
        if (args[0] == null){
            return this.defaultHandling(result, sender, command, label);
        }
        //if no handlers are registered to this command/alias, return result as not able to handle command
        if (!this.isAbleToHandle(args[0])){
            return new CommandResult().setSuccess(false);
        }
        final ICommandHandler handler = this.findHandler(this.findBase(args[0]));
        //declines command, if the commandSender-type doesn't match to the required one
        if (!Objects.requireNonNull(this.findBase(args[0])).checkReceiverType(sender)){
            return result.setSuccess(false).setMatchingReceiverType(false);
        }
        //finally handle the command, with a try/catch-block
        try {
            handler.handleCommand(sender, command, label, args);
        }catch (final Exception ex){
            result.appendException(ex);
            return result.setSuccess(false);
        }
        return result.setSuccess(true);
    }

    // handle commands without any arguments / specifications
    @Override
    public CommandResult defaultHandling(@NotNull CommandResult result, @NotNull CommandSender sender, Command command, String label) throws CommandException {
        sender.sendMessage("hello world 001");
        return result.setSuccess(true);
    }

    @Override
    public boolean registerHandler(BaseCommand command, ICommandHandler handler) {
        if (this.register.containsKey(command)){
            throw new KeyAlreadyExistsException("command already exists");
        }
        try {
            this.register.put(command, handler);
        }catch (final Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeHandler(String label) {
        if (!this.register.containsKey(this.findBase(label))){
            return false;
        }
        this.register.remove(this.findBase(label));
        return false;
    }

    @Nullable
    private BaseCommand findBase(final String label){
        for (Map.Entry<BaseCommand, ICommandHandler> entry : this.register.entrySet()){
            if (entry.getKey().isAppropriate(label)) return entry.getKey();
        }
        return null;
    }

    private ICommandHandler findHandler(final BaseCommand command){
        return this.register.get(command);
    }

    //checks if a command is able to get handled by a CommandHandler
    public boolean isAbleToHandle(final String label){
        return this.findBase(label) != null && this.findHandler(findBase(label)) != null;
    }

    {
        this.register = new HashMap<>();
    }
}

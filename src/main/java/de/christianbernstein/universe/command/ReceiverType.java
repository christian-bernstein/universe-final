package de.christianbernstein.universe.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum ReceiverType {

    VIRTUAL_USER, CONSOLE, NONE_SPECIFIED;

    public boolean isValid(final CommandSender sender){
        switch (this){
            case CONSOLE:
                return !(sender instanceof Player);
            case VIRTUAL_USER:
                return (sender instanceof Player);
            case NONE_SPECIFIED:
                return true;
        }
        return true;
    }
}

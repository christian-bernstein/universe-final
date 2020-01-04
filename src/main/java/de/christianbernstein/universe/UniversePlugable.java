package de.christianbernstein.universe;

import de.christianbernstein.universe.admission.AdmissionProvider;
import de.christianbernstein.universe.admission.IAdmissionProvider;
import de.christianbernstein.universe.command.BaseCommand;
import de.christianbernstein.universe.command.CommandDispatcher;
import de.christianbernstein.universe.command.ReceiverType;
import de.christianbernstein.universe.command.commands.AdmissionCommandHandler;
import de.christianbernstein.universe.command.commands.DebugCommand;
import de.christianbernstein.universe.provider.IServiceProvider;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.Objects;

public final class UniversePlugable extends AbstractSpigotPlugable implements IServiceProvider {

    final AdmissionProvider provider = new AdmissionProvider(new File(this.getDataFolder() + "\\admissions.json"));

    private CommandDispatcher commandDispatcher;

    public UniversePlugable() throws Exception {
    }

    @Override
    public void onLoad() {
        commandDispatcher = new CommandDispatcher(){
            @Override
            public void initiate() {
                registerHandler(new BaseCommand("debug", "debug", ReceiverType.NONE_SPECIFIED, "d", "deb"), new DebugCommand());
                registerHandler(new BaseCommand("admission", "admission", ReceiverType.NONE_SPECIFIED, "ad", "perms"), new AdmissionCommandHandler());
            }
        };
    }

    @Override
    public void onEnable() {
        Objects.requireNonNull(Bukkit.getPluginCommand("universe")).setExecutor(this.commandDispatcher);
        try {
            provider.loadAdmissions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            provider.saveAdmissions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


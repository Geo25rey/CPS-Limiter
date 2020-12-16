package me.geo52rey.cpslimiter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.*;

public class Main extends JavaPlugin {

    public static final int DEFAULT_CPS_LIMIT = 20;
    private int maxCPS;

    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("CPS Limit", DEFAULT_CPS_LIMIT);
        config.options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager()
                .registerEvents(
                        new PlayerInteractListener(this::getMaxCPS),
                        this);
    }

    public int getMaxCPS() {
        return config.getInt("CPS Limit");
    }

}

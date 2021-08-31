package com.terraboxstudios.instantreplay.util;

import com.terraboxstudios.instantreplay.InstantReplay;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Config {

    private static FileConfiguration config;
    private static File configFile;

    private static final InstantReplay plugin = InstantReplay.getPlugin(InstantReplay.class);

    public static void loadConfig() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        config = plugin.getConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        configFile = new File(plugin.getDataFolder(), "config.yml");
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static String readColouredString(String path) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString(path)));
    }

    public static List<String> readColouredStringListAsList(String path) {
        return getConfig().getStringList(path)
                .stream()
                .map(s -> ChatColor.translateAlternateColorCodes('&', s))
                .collect(Collectors.toList());
    }

}

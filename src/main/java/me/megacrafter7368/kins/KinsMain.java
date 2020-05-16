package me.megacrafter7368.kins;

import me.megacrafter7368.kins.utils.KinsRegistration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KinsMain extends JavaPlugin {

    public static List<String> fullKins = new ArrayList<>();

    public static List<File> kinses = new ArrayList<>();

    private File f = getDataFolder();

    @Override
    public void onEnable() {
        ConfigVariables.varsFile = new File(getDataFolder(), "variables.yml");
        if (!ConfigVariables.varsFile.exists()) {
            try {
                getDataFolder().mkdir();
                ConfigVariables.varsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ConfigVariables.vars = YamlConfiguration.loadConfiguration(ConfigVariables.varsFile);

        KinsRegistration.resetKinses(this);

        KinsRegistration.registerAllEffects();
        KinsRegistration.registerAllConditions();
    }

    @Override
    public void onDisable() {

    }
}
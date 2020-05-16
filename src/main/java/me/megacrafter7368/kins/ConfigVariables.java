package me.megacrafter7368.kins;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigVariables {

    static FileConfiguration vars;
    static File varsFile;

    public static void setVariable(String name, Object val) {
        vars.set(name, val);
        try {
            vars.save(varsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getVariable(String name) {
        return vars.get(name);
    }

    public static void delVariable(String name) {
        vars.set(name, null);
        try {
            vars.save(varsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

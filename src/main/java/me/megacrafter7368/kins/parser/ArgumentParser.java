package me.megacrafter7368.kins.parser;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.utils.ArgumentType;
import me.megacrafter7368.kins.utils.Looper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class ArgumentParser {

    public static Object understandArgument(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            if (data.startsWith("\"") && data.endsWith("\"")) {
                return Looper.buildString(data);
            }
            else if (data.equals("doğru")) return true;
            else if (data.equals("yanlış")) return false;
            else return null;
        }
    }

    public static Object parseArgument(String[] arg, ArgumentType argType) {
        if (arg == null || arg[0] == null) return null;
        if (argType == ArgumentType.PLAYER) {
            return parsePlayer(arg[0]);
        }

        if (argType == ArgumentType.ITEM) {
            return parseItem(arg[0]);
        }

        if (argType == ArgumentType.NUMBER) {
            return parseNumber(arg[0]);
        }

        if (argType == ArgumentType.TEXT) {
            return parseText(arg);
        }

        if (argType == ArgumentType.WORD) {
            return parseWord(arg[0]);
        }
        return null;
    }

    public static Object parsePlayer(String arg) {
        return Bukkit.getPlayer(arg);
    }

    public static Object parseItem(String arg, Parameter... param) {
        String itemargs[] = arg.split(":");

        Object itemParsed = Parameter.parse(arg, param);
        if (itemParsed instanceof ItemStack) {
            return itemParsed;
        }

        Material m = Material.matchMaterial(itemargs[0].toUpperCase(Locale.ENGLISH));

        short data = 0;
        if (itemargs.length == 2) {
            try {
                data = Short.parseShort(itemargs[1]);
            } catch (NumberFormatException e) {
                return "hata:Item data tanınamadı!";
            }
        }

        if (m == null) {
            return "hata:Item materyali tanınamadı!";
        }
        return new ItemStack(m, 1, data);
    }

    public static Object parseNumber(String arg) {
        int num;
        try {
            num = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return "hata:Sayı tanınamadı!";
        }
        return num;
    }

    public static Object parseText(String[] args) {
        StringBuilder sb = new StringBuilder();
        boolean start = true;
        for (String s : args) {
            sb.append(start ? "" : " ").append(s);
            start = false;
        }
        return ChatColor.translateAlternateColorCodes('&', sb.toString());
    }

    public static Object parseWord(String arg) {
        return ChatColor.translateAlternateColorCodes('&', arg);
    }

}
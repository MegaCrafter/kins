package me.megacrafter7368.kins.utils;

import me.megacrafter7368.kins.Parameter;
import org.apache.commons.lang.WordUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class VariableReplacer {

    public static String replace(String line, Parameter... param) {
        String ret = line;
        for (Parameter p : param) {
            Object value = p.getValue();
            String replaceWith = "";

            if (value == null) continue;

            if (value instanceof String) {
                replaceWith = (String) value;
            }

            if (value instanceof Integer) {
                replaceWith = (int)value + "";
            }

            if (value instanceof Player) {
                replaceWith = ((Player) value).getDisplayName();
            }

            if (value instanceof ItemStack) {
//                if (!((ItemStack) value).hasItemMeta()) {
                replaceWith = WordUtils.capitalize(((ItemStack) value).getType().name().toLowerCase(Locale.ENGLISH).replace("_", " "));
//                } else {
//                    replaceWith = ((ItemStack) value).getItemMeta().getDisplayName();
//                }
            }

            ret = ret.replace("%"+p.getParseName()+"%", replaceWith);
        }
        return ret;
    }

}
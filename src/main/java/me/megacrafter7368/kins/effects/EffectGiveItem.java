package me.megacrafter7368.kins.effects;

import me.megacrafter7368.kins.Pair;
import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.utils.AdjunctReplacer;
import me.megacrafter7368.kins.utils.ErrorThrower;
import me.megacrafter7368.kins.utils.Looper;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

// alıcıya [x tane] [x isimli] [x açıklamalı] item ver

public class EffectGiveItem extends Effect {

    public EffectGiveItem() {
        super("ver");
    }

    public void parseAndExecute(String line, Parameter... param) {
        String data[] = line.split(" ");

        Object receiver = null;
        String recParseName = AdjunctReplacer.replaceToAdjunct(data[0]);
        for (Parameter p : param) {
            Object parsed = p.parse(recParseName);
            if (parsed != null) receiver = parsed;
        }

        if (!(receiver instanceof Player)) {
            ErrorThrower.error(line, "Item verilecek oyuncu bulunamadı!");
            return;
        }
        Player p = (Player) receiver;

        int amount = -1;

        String name = null;
        String lore = null;

        boolean strBuilding = false;
        for (int i = 1; i < data.length - 2; i++) {
            if (strBuilding) {
                Pair<String, Integer> pair = Looper.buildString(data, i);
                i = pair.getValue();

                if (data[i].equalsIgnoreCase("isimli")) {
                    name = pair.getKey();
                }

                if (data[i].equalsIgnoreCase("açıklamalı")) {
                    lore = pair.getKey();
                }
                strBuilding = false;

                continue;
            }

            if (data[i].equalsIgnoreCase("isimli")) {
                String nameParse = data[i - 1];
                Object parsed = Parameter.parse(nameParse, param);
                if (!(parsed instanceof String)) {
                    ErrorThrower.error(line, nameParse + " bir yazı değil!");
                    return;
                }
                name = (String) parsed;
                continue;
            }

            if (data[i].equalsIgnoreCase("açıklamalı")) {
                String loreParse = data[i - 1];
                Object parsed = Parameter.parse(loreParse, param);
                if (!(parsed instanceof String)) {
                    ErrorThrower.error(line, loreParse + " bir yazı değil!");
                    return;
                }
                lore = (String) parsed;
                continue;
            }

            if (data[i].equalsIgnoreCase("tane")) {
                String amountParseName = data[i - 1];
                Object parsed = Parameter.parse(amountParseName, param);
                if (parsed != null) {
                    if (!(parsed instanceof Integer)) {
                        ErrorThrower.error(line, amountParseName + " bir sayı değil!");
                        return;
                    }

                    amount = (int) parsed;
                    continue;
                }

                try {
                    amount = Integer.parseInt(amountParseName);
                } catch (NumberFormatException e) {
                    ErrorThrower.error(line, amountParseName + " bir sayı değil!");
                    return;
                }
            }

            if (data[i].startsWith("\"")) {
                strBuilding = true;
                i--;
            }
        }

        String itemParse = data[data.length - 2];
        Object itemParsed = Parameter.parse(itemParse, param);
        if (!(itemParsed instanceof ItemStack)) {
            if (itemParsed instanceof String && ((String) itemParsed).startsWith("hata:")) {
                ErrorThrower.error(line, itemParse + " anlaşılamadı:", ((String) itemParsed).replace("hata:", ""));
            } else {
                ErrorThrower.error(line, itemParse + " anlaşılamadı!");
            }
            return;
        }
        ItemStack item = (ItemStack) itemParsed;

        String[] loreArray;
        if (lore == null) loreArray = new String[0];
        else loreArray = lore.split(Pattern.quote("|n"));

        execute(p, amount, name, item.getType(), item.getDurability(), loreArray);
    }

    private void execute(Player p, int amount, String name, Material m, short data, String... lore) {
        ItemStack item;
        if (data == -1) {
            item = new ItemStack(m);
        } else {
            item = new ItemStack(m, 1, data);
        }

        if (amount != -1) item.setAmount(amount);

        ItemMeta meta = item.getItemMeta();

        if (name != null) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        if (lore.length != 0) {
            List<String> makeLore = new ArrayList<>(Arrays.asList(lore));
            makeLore.forEach(str -> str = ChatColor.translateAlternateColorCodes('&', str));
            meta.setLore(makeLore);
        }
        item.setItemMeta(meta);

        p.getInventory().addItem(item);
    }

}
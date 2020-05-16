package me.megacrafter7368.kins.effects;

import me.megacrafter7368.kins.ConfigVariables;
import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.utils.AdjunctReplacer;

// {değişken.%player%}'ı sil
public class EffectDeleteVariable extends Effect {
    public EffectDeleteVariable() {
        super("sil");
    }

    @Override
    public void parseAndExecute(String line, Parameter... param) {
        line = line.replace(" sil", "");
        line = AdjunctReplacer.replaceTheAdjunct(line);
        line = line.replace("{", "").replace("}", "");

        ConfigVariables.delVariable(line);
    }
}

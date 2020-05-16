package me.megacrafter7368.kins.effects;

import me.megacrafter7368.kins.ConfigVariables;
import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.parser.ArgumentParser;
import me.megacrafter7368.kins.utils.AdjunctReplacer;
import me.megacrafter7368.kins.utils.ErrorThrower;

import java.util.regex.Pattern;

// {değişken.ehu.%degg%} değişkenini değer'e ayarla
public class EffectSetVariable extends Effect {

    public EffectSetVariable() {
        super("ayarla");
    }

    @Override
    public void parseAndExecute(String line, Parameter... param) {
        String[] data = line.substring(0, line.length() - 7).split(Pattern.quote(" değişkenini "));

        if (data.length != 2) {
            ErrorThrower.error(line, "'Ayarla' eventi için yanlış sözdizimi!");
            return;
        }

        String varName = AdjunctReplacer.replaceTheAdjunct(data[0]).replace("{", "").replace("}", "");
        String valName = AdjunctReplacer.replaceToAdjunct(data[1]);

        Object argParsedVal = ArgumentParser.understandArgument(valName);

        if (argParsedVal != null) ConfigVariables.setVariable(varName, argParsedVal);
        else ConfigVariables.setVariable(varName, Parameter.parse(valName, param));
    }
}

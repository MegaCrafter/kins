package me.megacrafter7368.kins.conditions;

import me.megacrafter7368.kins.ConfigVariables;
import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.parser.ArgumentParser;

import java.util.regex.Pattern;

// eğer {para.%oyuncu%} değeri 70 ise:
public class ConditionIsValue extends Condition {

    @Override
    public boolean check(String line) {
        return Pattern.matches(".*" + Pattern.quote(" değeri ") + ".*", line);
    }

    @Override
    public boolean parseAndMeet(String line, Parameter... param) {
        String[] data = line.split(Pattern.quote(" değeri "));

        String varName = data[0];
        Object varValue;
        if (varName.startsWith("{")) {
            varName = varName.replace("{", "").replace("}", "");
            varValue = ConfigVariables.getVariable(varName);
        } else {
            varValue = Parameter.parse(varName, param);
        }

        String valName = data[1];

        Object argParsedVal = ArgumentParser.understandArgument(valName);

        if (argParsedVal != null) {
            return argParsedVal.equals(varValue);
        } else if ((argParsedVal = Parameter.parse(valName, param)) != null) {
            return argParsedVal.equals(varValue);
        } else {
            return varValue == null;
        }
    }

}

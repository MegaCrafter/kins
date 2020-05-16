package me.megacrafter7368.kins.parser;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.conditions.Condition;

public class ConditionParser {

    public static boolean parseAndCheck(String line, Parameter... param) {
        line = line.trim();
        line = line.substring(0, line.length()-1);
        line = line.replace("eğer ", "");
        line = line.replace(" ise", "");

        if (line.contains(" ve ") || line.contains(" veya ") || line.contains(" ya da ")) {
            String[] multicond = line.split("( ve | veya | ya da )");
            boolean check = false;
            for (int i = 0; i < multicond.length; i++) {
                String cond = multicond[i];

                if (i == 0) {
                    check = getResultOf(cond, param);
                } else {
                    String between = line.substring(0, line.indexOf(cond));
                    if (between.endsWith(" ve ")) {
                        check = check && getResultOf(cond);
                    } else if (between.endsWith(" veya ")) {
                        check = check || getResultOf(cond);
                    } else if (between.endsWith(" ya da ")) {
                        check = check ^ getResultOf(cond);
                    }
                }
            }
            return check;
        }

        return getResultOf(line, param);
    }

    private static boolean getResultOf(String cond, Parameter... param) {
        boolean invert = cond.endsWith(" değil");
        if (invert) cond = cond.substring(0, cond.length()-6);

        for (Condition c : Condition.conditions) {
            if (c.check(cond)) {
                boolean meet = c.parseAndMeet(cond, param);
                return (meet != invert);
            }
        }
        return false;
    }

}
package me.megacrafter7368.kins.parser;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.utils.AdjunctReplacer;

public class ExpressionParser {

    public static Object parseExpression(String expr, Parameter... param) {
        String[] data = expr.split(" ");

        String subjectString = data[0];
        subjectString = AdjunctReplacer.replaceOfAdjunct(subjectString);
        Object subjectParsed = Parameter.parse(subjectString, param);
        if (subjectParsed == null) {
            // TODO: ERROR
            return null;
        }

        if (data.length == 1) {
            return subjectParsed;
        }

        String info = expr.substring(data[0].length() + 1);

        /*
        TODO: LOOP EXPRESSIONS AND RETURN THE MOST USEFUL ONE
         */

        return null;
    }

}
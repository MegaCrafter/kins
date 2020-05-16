package me.megacrafter7368.kins.conditions;

import me.megacrafter7368.kins.Parameter;

public class ConditionIsSet extends Condition {

    @Override
    public boolean check(String line) {
        return (line.endsWith(" ayarlı"));
    }

    @Override
    public boolean parseAndMeet(String line, Parameter... param) {
        String obj = line.split(" ")[0];
        Object parsed = Parameter.parse(obj, param);
        return parsed != null;
    }
}
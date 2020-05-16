package me.megacrafter7368.kins.conditions;

import me.megacrafter7368.kins.Parameter;

import java.util.ArrayList;

public abstract class Condition {

    public static ArrayList<Condition> conditions = new ArrayList<>();

    public Condition() {
        conditions.add(this);
    }

    public abstract boolean check(String line);
    public abstract boolean parseAndMeet(String line, Parameter... param);
}

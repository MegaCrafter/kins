package me.megacrafter7368.kins.effects;

import me.megacrafter7368.kins.Parameter;

import java.util.ArrayList;

public abstract class Effect {

    public static ArrayList<Effect> effects = new ArrayList<>();

    private String indicator;
    public Effect(String indicator) {
        this.indicator = indicator;

        effects.add(this);
    }

    public String getIndicator() {
        return indicator;
    }

    public abstract void parseAndExecute(String line, Parameter... param);
}

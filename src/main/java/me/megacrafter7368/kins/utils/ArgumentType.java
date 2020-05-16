package me.megacrafter7368.kins.utils;

public enum ArgumentType {

    WORD("<kelime>"), TEXT("<yazı>"), NUMBER("<sayı>"), PLAYER("<oyuncu>"), ITEM("<item>");

    private String name;
    private boolean optional;
    ArgumentType(String name) {
        this.name = name;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public static ArgumentType getByName(String name) {
        for (ArgumentType at : ArgumentType.values()) {
            if (at.getName().equalsIgnoreCase(name)) return at;
        }
        return null;
    }
}
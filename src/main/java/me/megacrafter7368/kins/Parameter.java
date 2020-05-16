package me.megacrafter7368.kins;

public class Parameter<T> {

    private String parseName;
    private T value;
    public Parameter(String parseName, T value) {
        this.parseName = parseName;
        this.value = value;
    }

    public String getParseName() {
        return parseName;
    }

    public T getValue() {
        return value;
    }

    public T parse(String name) {
        if (this.parseName.equalsIgnoreCase(name)) {
            return value;
        } else {
            return null;
        }
    }

    public static Object parse(String pn, Parameter... param) {
        Object varObj = null;
        for (Parameter p : param) {
            Object parsed = p.parse(pn);
            if (parsed != null) {
                varObj = parsed;
                break;
            }
        }
        return varObj;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "parseName='" + parseName + '\'' +
                ", value=" + value +
                '}';
    }
}
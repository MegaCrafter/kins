package me.megacrafter7368.kins.utils;

public class ErrorThrower {

    public static void error(String line, String... error) {
        System.err.println("------------------------------------");
        System.err.println("KazuroIns Hata:");
        System.err.println("-> Satır:");
        System.err.println("- -> " + line.replace("    ", "[TAB]"));
        System.err.println("-> Hata:");
        for (String err : error) {
            System.err.println("- -> " + err);
        }
        System.err.println("------------------------------------");
    }

}
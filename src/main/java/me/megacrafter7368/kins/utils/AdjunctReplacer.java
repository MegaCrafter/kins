package me.megacrafter7368.kins.utils;

public class AdjunctReplacer {

    public static String replaceToAdjunct(String data) {
        if (data.endsWith("'e") || data.endsWith("'a") || data.endsWith("ya") || data.endsWith("ye")) {
            data = data.substring(0, data.length() - 2);
        }
        return data;
    }

    public static String replaceFromAdjunct(String data) {
        if (data.endsWith("'den") || data.endsWith("'dan") || data.endsWith("'tan") || data.endsWith("'ten")) {
            data = data.substring(0, data.length() - 4);
        } else if (data.endsWith("dan") || data.endsWith("den") || data.endsWith("tan") || data.endsWith("ten")) {
            data = data.substring(0, data.length() - 3);
        }

        return data;
    }

    public static String replaceTheAdjunct(String data) {
        if (data.endsWith("'i") || data.endsWith("'ı") || data.endsWith("yi") || data.endsWith("yı") || data.endsWith("ni") ||
            data.endsWith("nı") || data.endsWith("'u") || data.endsWith("'ü") || data.endsWith("yu") || data.endsWith("yü") ||
            data.endsWith("nu") || data.endsWith("nü")) {

            data = data.substring(0, data.length() - 2);

        }

        return data;
    }

    public static String replaceOfAdjunct(String data) {
        if (data.endsWith("nın") || data.endsWith("nin") || data.endsWith("nun") || data.endsWith("nün") ||
            data.endsWith("'ın") || data.endsWith("'in") || data.endsWith("'un") || data.endsWith("'ün")) {

            data = data.substring(0, data.length() - 3);
        }

        return data;
    }
}
package me.megacrafter7368.kins.utils;

import me.megacrafter7368.kins.Pair;

import java.util.ArrayList;
import java.util.List;

public class Looper {

    // ILK SATIRI ALMAZ
    public static List<String> loopToBlockEnd(List<String> lines, int currentIndex) {
        return loopToBlockEnd(lines, currentIndex, false);
    }

    public static List<String> loopToBlockEnd(List<String> lines, int currentIndex, boolean addFirstLine) {
        List<String> block = new ArrayList<>();
        String tabString = lines.get(currentIndex);

        if (addFirstLine) block.add(tabString);

        int delWhitespaces = 0;
        while (tabString.startsWith("    ")) {
            tabString = tabString.substring(4);
            delWhitespaces += 4;
        }

        for (int i = currentIndex + 1; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (currentLine.trim().isEmpty()) break; // SATIR TAMAMEN BOŞSA BIRAK
            if (!currentLine.substring(delWhitespaces).startsWith("    ")) break; // BLOĞUN İÇİNDE OLMAYAN BİR SATIRA GELİNDİĞİNDE BIRAK
            block.add(currentLine);
        }

        return block;
    }

    public static Pair<String, Integer> buildString(String data[], int curIndex) {
        StringBuilder tempBuilder = new StringBuilder();

        int lastIndex = curIndex;
        for (int i = curIndex; i < data.length; i++) {
            lastIndex = i;
            if (data[i].startsWith("\"")) {
                data[i] = data[i].substring(1);
            }

            String word = data[i].replace("\\", "");

            if (!data[i].endsWith("\\\"") && data[i].endsWith("\"")) {
                tempBuilder.append(word, 0, word.length() - 1);
                break;
            }

            tempBuilder.append(word).append(" ");
        }

        return new Pair<>(tempBuilder.toString(), lastIndex + 1);
    }

    public static String buildString(String str) {
        StringBuilder tempBuilder = new StringBuilder();
        String[] data = str.split(" ");

        for (int i = 0; i < data.length; i++) {
            if (data[i].startsWith("\"")) {
                data[i] = data[i].substring(1);
            }

            String word = data[i].replace("\\", "");

            if (!data[i].endsWith("\\\"") && data[i].endsWith("\"")) {
                tempBuilder.append(word, 0, word.length() - 1);
                break;
            }

            tempBuilder.append(word).append(" ");
        }

        return tempBuilder.toString();
    }

}
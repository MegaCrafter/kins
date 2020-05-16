package me.megacrafter7368.kins.utils;

import me.megacrafter7368.kins.KinsMain;
import me.megacrafter7368.kins.conditions.ConditionIsSet;
import me.megacrafter7368.kins.conditions.ConditionIsValue;
import me.megacrafter7368.kins.effects.*;
import me.megacrafter7368.kins.events.KinsEvent;
import me.megacrafter7368.kins.parser.EventParser;
import org.bukkit.Bukkit;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class KinsRegistration {

    public static void resetKinses(KinsMain kinsMain) {
        File kinsesFolder = new File(kinsMain.getDataFolder(), "kinses");
        if (!kinsesFolder.exists()) {
            kinsesFolder.mkdir();

            try {
                File startkins = new File(kinsMain.getDataFolder() + "/kinses", "start.kins");

                startkins.createNewFile();

                PrintWriter writer = new PrintWriter(startkins);
                BufferedReader reader = new BufferedReader(new InputStreamReader(KinsRegistration.class.getResourceAsStream("/start.kins")));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }

                writer.flush();
                writer.close();

                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File[] kinsesArray = kinsesFolder.listFiles((dir, name) -> name.endsWith(".kins") && !name.startsWith("--"));

        KinsMain.fullKins = new ArrayList<>();
        if (kinsesArray != null) {
            KinsMain.kinses = Arrays.asList(kinsesArray);
            for (File f : KinsMain.kinses) {
                try {
                    KinsMain.fullKins.addAll(Files.readAllLines(f.toPath(), StandardCharsets.UTF_8).stream().filter(str -> !str.replaceAll(" ", "").isEmpty()).collect(Collectors.toList()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        resetEvents(kinsMain);
    }

    private static ArrayList<Class<? extends KinsEvent>> eventClassesToBeReset = new ArrayList<>();
    public static void resetEvents(KinsMain kinsMain) {
        KinsEvent.events = new ArrayList<>();

        eventClassesToBeReset.forEach(cl -> {
            try {
                cl.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        EventParser.registerAll();
        for (KinsEvent e : KinsEvent.events) {
            Bukkit.getPluginManager().registerEvents(e, kinsMain);
        }
    }

    public static void registerEvent(Class<? extends KinsEvent> cl) {
        eventClassesToBeReset.add(cl);
    }

    public static void registerAllEffects() {
        new EffectSend();
        new EffectGiveItem();
        new EffectSetVariable();
        new EffectDeleteVariable();
        new EffectCancelEvent();
    }

    public static void registerAllConditions() {
        new ConditionIsSet();
        new ConditionIsValue();
    }

}
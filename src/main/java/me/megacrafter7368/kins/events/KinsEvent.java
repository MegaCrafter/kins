package me.megacrafter7368.kins.events;

import me.megacrafter7368.kins.utils.KinsRegistration;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class KinsEvent implements Listener {

    public static ArrayList<KinsEvent> events = new ArrayList<>();
    public static Event currentEvent = null;

    public KinsEvent() {
        events.add(this);
    }

    public abstract boolean check(String line);
    public abstract void parseThrough(List<String> lines);

    static {
        KinsRegistration.registerEvent(EventCommand.class);
        KinsRegistration.registerEvent(EventChat.class);
    }
}

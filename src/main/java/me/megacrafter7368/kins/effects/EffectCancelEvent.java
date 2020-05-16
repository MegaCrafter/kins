package me.megacrafter7368.kins.effects;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.events.KinsEvent;
import me.megacrafter7368.kins.utils.ErrorThrower;
import org.bukkit.event.Cancellable;

public class EffectCancelEvent extends Effect {
    public EffectCancelEvent() {
        super("iptal et");
    }

    @Override
    public void parseAndExecute(String line, Parameter... param) {
        if (KinsEvent.currentEvent instanceof Cancellable) {
            ((Cancellable) KinsEvent.currentEvent).setCancelled(true);
        } else {
            ErrorThrower.error(line, "Bu event iptal edilemez!");
        }
    }
}

package me.megacrafter7368.kins.events;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.ins.Instruction;
import me.megacrafter7368.kins.ins.InstructionType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.regex.Pattern;

// sohbete (mesaj|bir şey) yazıl(dığında|ınca)
public class EventChat extends KinsEvent {
    @Override
    public boolean check(String line) {
        return Pattern.matches(Pattern.quote("sohbete ") + "(mesaj |bir şey |)" + Pattern.quote("yazıl") + "(dığında|ınca|ırsa)", line.substring(0, line.length()-1));
    }

    @Override
    public void parseThrough(List<String> lines) {
        new Instruction(InstructionType.CHAT, lines.subList(1, lines.size()));
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        for (Instruction ins : Instruction.getInstructions()) {
            if (ins.getType() == InstructionType.CHAT) {
                currentEvent = e;
                ins.execute(new Parameter<>("oyuncu", e.getPlayer()), new Parameter<>("mesaj", e.getMessage()), new Parameter<>("format", e.getFormat()));
            }
        }
    }
}

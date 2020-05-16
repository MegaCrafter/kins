package me.megacrafter7368.kins.parser;

import me.megacrafter7368.kins.KinsMain;
import me.megacrafter7368.kins.events.KinsEvent;
import me.megacrafter7368.kins.utils.ErrorThrower;
import me.megacrafter7368.kins.utils.Looper;

import java.util.List;

public class EventParser {

    public static void registerAll() {
        lineterator:
        for (int i = 0; i < KinsMain.fullKins.size(); i++) {
            try {
                String line = KinsMain.fullKins.get(i);
                if (line.trim().isEmpty() || line.startsWith(" ")) continue;

                for (KinsEvent e : KinsEvent.events) {
                    if (e.check(line)) {
                        List<String> loop = Looper.loopToBlockEnd(KinsMain.fullKins, i, true);
                        e.parseThrough(loop);
                        i += loop.size() - 1;
                        continue lineterator;
                    }
                }

                ErrorThrower.error(line, "Event anlaşılamadı!");
            } catch (IndexOutOfBoundsException ignored) {}
        }

    }

}

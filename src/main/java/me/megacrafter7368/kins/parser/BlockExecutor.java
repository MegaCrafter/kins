package me.megacrafter7368.kins.parser;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.effects.Effect;
import me.megacrafter7368.kins.utils.ErrorThrower;
import me.megacrafter7368.kins.utils.Looper;
import me.megacrafter7368.kins.utils.VariableReplacer;

import java.util.List;

public class BlockExecutor {

    public static void execute(List<String> lines, Parameter... param) {
        lineloop:
        for (int i = 0; i < lines.size(); i++) {
            try {
                String line = VariableReplacer.replace(lines.get(i), param);

                if (line.trim().startsWith("eğer ") || line.endsWith(" ise:")) {
                    List<String> block = Looper.loopToBlockEnd(lines, i);
                    i += block.size() + 1; // IF BLOĞUNUN BİTTİĞİ İLK SATIR
                    if (ConditionParser.parseAndCheck(line, param)) {
                        execute(block, param);
                        if (lines.get(i).trim().equalsIgnoreCase("değilse:")) {
                            List<String> elseblock = Looper.loopToBlockEnd(lines, i);
                            i += elseblock.size() + 1;
                        }
                    } else {
                        if (lines.get(i).trim().equalsIgnoreCase("değilse:")) {
                            List<String> elseblock = Looper.loopToBlockEnd(lines, i);
                            execute(elseblock, param);
                            i += elseblock.size() + 1;
                        }
                    }
                }
                line = VariableReplacer.replace(lines.get(i), param);

                for (Effect e : Effect.effects) {
                    if (line.endsWith(" " + e.getIndicator())) {
                        e.parseAndExecute(line.trim(), param);
                        continue lineloop;
                    }
                }

                ErrorThrower.error(line, "Satır anlaşılamadı!");
            } catch (IndexOutOfBoundsException ignored) {}
        }
    }

}
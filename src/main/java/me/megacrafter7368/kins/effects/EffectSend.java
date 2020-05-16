package me.megacrafter7368.kins.effects;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.utils.AdjunctReplacer;
import me.megacrafter7368.kins.utils.ErrorThrower;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

// alıcıya "mesaj" mesajı gönder
public class EffectSend extends Effect {

    public EffectSend() {
        super("mesajı gönder");
    }

    public void parseAndExecute(String line, Parameter... param) {
        String data[] = line.split(" ");

        boolean msgPart = false;

        StringBuilder receiveBuilder = new StringBuilder();
        StringBuilder msgBuilder = new StringBuilder();

        boolean start = true;

        for (int i = 0; i < data.length; i++) {
            if (!msgPart) {
                if (data[i].startsWith("\"")) {
                    msgPart = true;
                    i--;
                    continue;
                }

                if (!start) receiveBuilder.append(" ");
                receiveBuilder.append(data[i]);
                start = false;
            } else {
                if (data[i].startsWith("\"")) {
                    data[i] = data[i].substring(1);
                }

                String word = data[i].replace("\\", "");

                if (!data[i].endsWith("\\\"") && data[i].endsWith("\"")) {
                    msgBuilder.append(word, 0, word.length() - 1);
                    break;
                }

                msgBuilder.append(word).append(" ");
            }
        }

        Object receiver = null;
        String recParseName = AdjunctReplacer.replaceToAdjunct(receiveBuilder.toString());
        for (Parameter p : param) {
            Object parsed = p.parse(recParseName);
            if (parsed != null) {
                receiver = parsed;
                break;
            }
        }

        String msg = ChatColor.translateAlternateColorCodes('&', msgBuilder.toString());

        if (!(receiver instanceof CommandSender)) {
            ErrorThrower.error(line, "Mesaj gönderilecek hedef bulunamadı!");
            return;
        }
        CommandSender cs = (CommandSender) receiver;

        execute(cs, msg);
    }

    private void execute(CommandSender receiver, String msg) {
        receiver.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

}
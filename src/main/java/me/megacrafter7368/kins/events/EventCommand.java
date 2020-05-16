package me.megacrafter7368.kins.events;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.ins.InsCommand;
import me.megacrafter7368.kins.ins.Instruction;
import me.megacrafter7368.kins.ins.InstructionType;
import me.megacrafter7368.kins.parser.ArgumentParser;
import me.megacrafter7368.kins.utils.ArgumentType;
import me.megacrafter7368.kins.utils.ErrorThrower;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EventCommand extends KinsEvent {

    @Override
    public boolean check(String line) {
        return (line.startsWith("komut /"));
    }

    @Override
    public void parseThrough(List<String> lines) {
        boolean triggerStarted = false;

        List<String> names = new ArrayList<>();
        String perm = "", permMsg = "";
        List<ArgumentType> argTypes = new ArrayList<>();
        List<String> trigger = new ArrayList<>();

        lineterator:
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (i == 0) {
                String args[] = line.replace("komut /", "").replace(":", "").split(" ");

                if (args.length >= 1) names.add(args[0].toLowerCase());
                for (int ai = 1; ai < args.length; ai++) {
                    boolean optional = args[ai].contains("[");

                    ArgumentType at = ArgumentType.getByName(args[ai].replace("[", "").replace("]", ""));

                    if (at == null) {
                        ErrorThrower.error(line, args[ai] + " bir argüman tipi olarak tanımlanamadı!");
                        continue lineterator;
                    }

                    at.setOptional(optional);

                    argTypes.add(at);
                }
                continue;
            }

            if (triggerStarted) {
                if (line.startsWith("        ")) {
                    line = line.substring(8);
                } else break;

                trigger.add(line);
                continue;
            }

            if (line.startsWith("    ")) {
                line = line.trim();
            } else break;

            if (line.startsWith("yetki: ")) {
                perm = line.replace("yetki: ", "").toLowerCase();
            }

            if (line.startsWith("yetki mesajı: ")) {
                permMsg = ChatColor.translateAlternateColorCodes('&', line.replace("yetki mesajı: ", ""));
            }

            if (line.startsWith("diğer: ")) {
                names.addAll(Arrays.asList(line.replace("diğer: ", "").split(",")));
            }

            if (line.startsWith("tetikle:")) {
                triggerStarted = true;
            }

        }
        new InsCommand(names, argTypes, perm, permMsg, trigger);
    }

    @EventHandler
    public void command(PlayerCommandPreprocessEvent e) {
        for (Instruction ins : Instruction.getInstructions()) {
            if (ins.getType() == InstructionType.COMMAND) {
                InsCommand cmd = (InsCommand) ins;

                String cmdname = e.getMessage().toLowerCase(Locale.ENGLISH).replace("/", "").split(" ")[0];
                if (cmd.getNames().contains(cmdname)) {
                    e.setCancelled(true);
                    if (cmd.getPerm().equals("") || e.getPlayer().hasPermission(cmd.getPerm())) {

                        // TODO: ARGUMENT PARSING
                        String[] args = e.getMessage().replace(e.getMessage().split(" ")[0] + " ", "").split(" ");
                        Parameter[] parsed = new Parameter[cmd.getArgTypes().size() + 2];
                        for (int i = 0; i < cmd.getArgTypes().size(); i++) {

                            String arg = null;
                            try {
                                arg = args[i];
                            } catch (IndexOutOfBoundsException exception) {
                                if (!cmd.getArgTypes().get(i).isOptional()) {
                                    e.getPlayer().sendMessage(ChatColor.RED + "Komut argümanları hatalı!");
                                    return;
                                }
                            }

                            ArgumentType at = cmd.getArgTypes().get(i);
                            String s[];

                            if (arg != null) {
                                if (at != ArgumentType.TEXT) {
                                    s = new String[]{arg};
                                    parsed[i + 2] = new Parameter<>("değer-" + (i + 1), ArgumentParser.parseArgument(s, at));
                                } else {
                                    s = new String[args.length - i];

                                    System.arraycopy(args, i, s, 0, args.length - i);

                                    parsed[i + 2] = new Parameter<>("değer-" + (i + 1), ArgumentParser.parseText(s));
                                    break;
                                }
                            } else {
                                parsed[i + 2] = new Parameter<>("değer-" + (i + 1), null);
                            }
                        }
                        // TODO: ARGUMENT PARSING

                        parsed[0] = new Parameter<>("oyuncu", e.getPlayer());
                        parsed[1] = new Parameter<>("gönderici", e.getPlayer());

                        currentEvent = e;
                        cmd.execute(parsed);

                    } else {
                        e.getPlayer().sendMessage(cmd.getPermMsg());
                    }

                    break;
                }
            }
        }
    }

    @EventHandler
    public void command(ServerCommandEvent e) {
        for (Instruction ins : Instruction.getInstructions()) {
            if (ins.getType() == InstructionType.COMMAND) {
                InsCommand cmd = (InsCommand) ins;
                String cmdname = e.getCommand().toLowerCase(Locale.ENGLISH).split(" ")[0];
                if (cmd.getNames().contains(cmdname)) {
                    // TODO: ARGUMENT PARSING
                    String args[] = e.getCommand().toLowerCase(Locale.ENGLISH).replace(cmdname + " ", "").split(" ");
                    Parameter parsed[] = new Parameter[cmd.getArgTypes().size() + 2];
                    for (int i = 0; i < cmd.getArgTypes().size(); i++) {

                        String arg = null;
                        try {
                            arg = args[i];
                        } catch (IndexOutOfBoundsException exception) {
                            if (!cmd.getArgTypes().get(i).isOptional()) {
                                e.getSender().sendMessage(ChatColor.RED + "Komut argümanları hatalı!");
                                return;
                            }
                        }

                        ArgumentType at = cmd.getArgTypes().get(i);
                        String s[];
                        if (at != ArgumentType.TEXT) {
                            s = new String[]{arg};
                            parsed[i + 2] = new Parameter<>("değer-" + i, ArgumentParser.parseArgument(s, at));
                        } else {
                            s = new String[args.length - i];

                            System.arraycopy(args, i, s, 0, args.length - i);

                            parsed[i + 2] = new Parameter<>("değer-" + i, ArgumentParser.parseText(s));
                            break;
                        }
                    }
                    // TODO: ARGUMENT PARSING

                    parsed[0] = new Parameter<>("konsol", e.getSender());
                    parsed[1] = new Parameter<>("gönderici", e.getSender());

                    cmd.execute(parsed);

                    break;
                }
            }
        }
    }
}
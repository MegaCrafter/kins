package me.megacrafter7368.kins.ins;

import me.megacrafter7368.kins.utils.ArgumentType;
import org.bukkit.ChatColor;

import java.util.List;

public class InsCommand extends Instruction {

    private String perm, permMsg;
    private List<String> names;
    private List<ArgumentType> argTypes;

    public InsCommand(List<String> names, List<ArgumentType> argTypes, String perm, String permMsg, List<String> trigger) {
        super(InstructionType.COMMAND, trigger);

        this.names = names;
        this.perm = perm;
        this.permMsg = ChatColor.translateAlternateColorCodes('&', permMsg);
        this.argTypes = argTypes;
    }

    public List<String> getNames() {
        return names;
    }

    public String getPerm() {
        return perm;
    }

    public String getPermMsg() {
        return permMsg;
    }

    public List<ArgumentType> getArgTypes() {
        return argTypes;
    }

}
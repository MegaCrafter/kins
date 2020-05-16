package me.megacrafter7368.kins.ins;

import me.megacrafter7368.kins.Parameter;
import me.megacrafter7368.kins.parser.BlockExecutor;

import java.util.ArrayList;
import java.util.List;

public class Instruction {

    private static ArrayList<Instruction> instructions = new ArrayList<>();

    private InstructionType type;
    private List<String> toExecute;
    public Instruction(InstructionType type, List<String> toExecute) {
        this.type = type;
        this.toExecute = toExecute;

        instructions.add(this);
    }

    public List<String> getLines() {
        return toExecute;
    }

    public InstructionType getType() {
        return type;
    }

    public void execute(Parameter... param) {
        BlockExecutor.execute(toExecute, param);
    }

    public static ArrayList<Instruction> getInstructions() {
        return instructions;
    }

}

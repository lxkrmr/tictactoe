package com.example.tictactoe.domain;

import lombok.Value;

@Value
public class Instruction {

    String value;

    public static Instruction instruction(String instruction) {
        return new Instruction(instruction);
    }
}

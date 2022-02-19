package com.example.tictactoe.domain.player;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NumberOfReloads {

    int value;

    public static NumberOfReloads initNumberOfReloads() {
        return new NumberOfReloads(0);
    }

    public void reset() {
        this.value = 0;
    }

    public void increment() {
        this.value++;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

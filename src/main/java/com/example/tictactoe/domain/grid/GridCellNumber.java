package com.example.tictactoe.domain.grid;

import lombok.Value;

@Value
public class GridCellNumber {

    int value;

    public static GridCellNumber gridCellNumber(int number) {
        return new GridCellNumber(number);
    }

    public boolean isValidIndex(int maxIndexExcluded) {
        var valueAsIndex = toIndex();
        return valueAsIndex >= 0 && valueAsIndex < maxIndexExcluded;
    }

    public int toIndex() {
        return value - 1;
    }
}

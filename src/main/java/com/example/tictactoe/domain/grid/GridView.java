package com.example.tictactoe.domain.grid;

import lombok.Value;

@Value
public class GridView {

    public static final String TEMPLATE = String.join("\n",
                                                      " %s | %s | %s",
                                                      "-----------",
                                                      " %s | %s | %s",
                                                      "----------",
                                                      " %s | %s | %s");
    String view;

    public static GridView gridView(Grid grid) {
        var view = String.format(TEMPLATE,
                                 grid.getCells()
                                     .stream()
                                     .map(GridCell::getValue)
                                     .toArray());

        return new GridView(view);
    }


}

package com.toll.tetris.figures;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by toll on 30.01.16.
 */
public enum FigRotation {
    NORM,
    ROT_90,
    ROT_180,
    ROT_270;

    private static int rotationsAmount = -1;

    public FigRotation GetNextRotation() {
        if (map == null) {
            InitMap();
        }

        return map.get((this.ordinal() + 1) % rotationsAmount);
    }

    private static Map<Integer, FigRotation> map;
    private void InitMap()
    {
        if (map == null) {
            map = new HashMap<Integer, FigRotation>();

            for(FigRotation state: FigRotation.values()) {
                map.put(state.ordinal(), state);
            }

            rotationsAmount = FigRotation.values().length;
        }
    }
}

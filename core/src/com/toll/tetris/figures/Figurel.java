package com.toll.tetris.figures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by toll on 30.01.16.
 */
public class Figurel extends AFigure{


    public Figurel(TextureRegion texture) {
        super(texture);
        rotationMasks = new int[][] {
                {1, 1, 1, 1,
                 0, 0, 0, 0,
                 0, 0, 0, 0,
                 0, 0, 0, 0,
                },

                {1, 0, 0, 0,
                 1, 0, 0, 0,
                 1, 0, 0, 0,
                 1, 0, 0, 0,
                },

                {1, 1, 1, 1,
                 0, 0, 0, 0,
                 0, 0, 0, 0,
                 0, 0, 0, 0,
                },

                {1, 0, 0, 0,
                 1, 0, 0, 0,
                 1, 0, 0, 0,
                 1, 0, 0, 0,
                },
        };

        width = new int[] {4, 1, 4, 1};
        height = new int[] {1, 4, 1, 4};
    }
}

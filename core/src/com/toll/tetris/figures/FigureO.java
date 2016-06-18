package com.toll.tetris.figures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by toll on 30.01.16.
 */
public class FigureO extends AFigure{


    public FigureO(TextureRegion texture) {
        super(texture);
        rotationMasks = new int[][]{
                {1, 1, 0, 0,
                 1, 1, 0, 0,
                 0, 0, 0, 0,
                 0, 0, 0, 0,
                },

                {1, 1, 0, 0,
                 1, 1, 0, 0,
                 0, 0, 0, 0,
                 0, 0, 0, 0,
                },

                {1, 1, 0, 0,
                 1, 1, 0, 0,
                 0, 0, 0, 0,
                 0, 0, 0, 0,
                },

                {1, 1, 0, 0,
                 1, 1, 0, 0,
                 0, 0, 0, 0,
                 0, 0, 0, 0,
                }
        };

        width = new int[] {2, 2, 2, 2};
        height = new int[] {2, 2, 2, 2};
    }

    @Override
    public void Rotate() {
        rotation.ordinal();
    }

}

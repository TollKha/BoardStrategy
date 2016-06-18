package com.toll.tetris;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by toll on 03.05.16.
 */
public class Cell {
    private ECellState state = ECellState.EMPTY;
    private TextureRegion textureRegion;

    public ECellState GetState() {
        return state;
    }

    public void SetTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public TextureRegion GetTextureRegion() {
        return textureRegion;
    }


    public void MarkBlocked() {
        state = ECellState.BLOCK;
    }
}

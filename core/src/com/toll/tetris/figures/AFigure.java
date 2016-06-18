package com.toll.tetris.figures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by toll on 30.01.16.
 */
public abstract class AFigure {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected int x;
    protected int y;
    protected FigRotation rotation;
    protected TextureRegion texture;
    protected int[][] rotationMasks;
    protected int[] width;
    protected int[] height;
    protected int blockSize;

    public AFigure(TextureRegion texture) {
        this.texture = texture;
        blockSize = texture.getRegionWidth();
        rotation = FigRotation.NORM;
    }

    public void Render(SpriteBatch batch, int startX, int startY)
    {
        int[] mask = GetMask();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (mask[y * 4 + x] == 1) {
                    batch.draw(texture, startX + blockSize * x, startY + blockSize * y);
                }
            }
        }
    }

    public int[] GetMask() {
        return GetMask(rotation);
    }

    public int[] GetMask(FigRotation rotation) {
        return rotationMasks[rotation.ordinal()];
    }

    public void Rotate() {
        rotation = rotation.GetNextRotation();
    }
    public static int GetId(){return -1;};
    public FigRotation GetRotation() {
      return rotation;
    };

    public void SetPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void MoveDown() {
        if (y > 0)
            y--;
    }

    public void MoveLeft() {
        x--;
    }

    public void MoveRight() {
        x++;
    }

    public int[][] GetMasks() {
        return rotationMasks;
    }


    public int GetHeight(FigRotation rotation) {
        return height[rotation.ordinal()];
    }

    public int GetWidth(FigRotation rotation) {
        return width[rotation.ordinal()];
    }

    public int GetHeight() {
        return GetHeight(rotation);
    }

    public int GetWidth() {
        return GetWidth(rotation);
    }

    public TextureRegion GetTexture() {
        return texture;
    }
}

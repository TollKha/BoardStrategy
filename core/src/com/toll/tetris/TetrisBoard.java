package com.toll.tetris;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.toll.tetris.figures.AFigure;
import com.toll.tetris.figures.FigRotation;

/**
 * Created by toll on 23.01.16.
 */
public class TetrisBoard extends Board {
    //Blocks in the width
    private final static int SIZE_X = 10;

    //Blocks in the height
    private final static int SIZE_Y = 20;

    public TetrisBoard(TextureRegion backgroundBlock) {
        super(SIZE_X, SIZE_Y, backgroundBlock);
    }

    public boolean CanMoveDown(AFigure figure) {
        return true;
    }

    public void AddFigure(AFigure figure) {
        int startX = figure.getX();
        int startY = figure.getY();

        int cellX, cellY;

        int[] mask = figure.GetMask();
        for (int figX = 0; figX < 4; figX++) {
            cellY = startY + figX;
            for (int figY = 0; figY < 4; figY++) {
                cellX = startX + figY;
                if (mask[figX * 4 + figY] == 1) {
                    cells[cellX][cellY].MarkBlocked();
                    cells[cellX][cellY].SetTextureRegion(figure.GetTexture());
                }
            }
        }
    }

    public boolean CheckCollision(AFigure figure, int posX, int posY) {
        return CheckCollision(figure, posX, posY, figure.GetRotation());
    }

    public boolean CheckCollision(AFigure figure, int posX, int posY, FigRotation rotation) {
        int[] mask = figure.GetMask(rotation);

        int fW = figure.GetWidth(rotation);
        int fH = figure.GetHeight(rotation);

        if (posX < 0 || posX > sizeX - fW || posY < 0)
            return true;

        int cellX, cellY;
        for (int figY = 0; figY < 4; figY++) {
            cellY = posY + figY;
            for (int figX = 0; figX < 4; figX++) {
                cellX = posX + figX;
                if (mask[figY * 4 + figX] == 1 && (cellX < 0 || cellX >= SIZE_X || cellY < 0 || cellY >= SIZE_Y || cells[cellX][cellY].GetState() == ECellState.BLOCK))
                    return true;
            }
        }

        return false;
    }
}

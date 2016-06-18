package com.toll.tetris;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by toll on 23.01.16.
 */
public abstract class Board {
    protected int sizeX;
    protected int sizeY;
    protected Cell[][] cells;

    protected TextureRegion backgroundBlock;
    public int boardBlockSize;


    public Board(int sizeX, int sizeY, TextureRegion backgroundBlock) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.backgroundBlock = backgroundBlock;

        boardBlockSize = backgroundBlock.getRegionWidth();
        cells = new Cell[sizeX][sizeY];
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[0].length; j++) {
                Cell newCell = new Cell();
                newCell.SetTextureRegion(backgroundBlock);
                cells[i][j] = newCell;
            }
        }
    }

    public int GetBoardWidth()
    {
        return sizeX;
    }

    public int GetBoardHeight()
    {
        return sizeY;
    }

    public void Render(SpriteBatch batch) {
        for (int i = 0; i < GetBoardWidth(); i++) {
            for (int j = 0; j < GetBoardHeight(); j++) {
                batch.draw(cells[i][j].GetTextureRegion(), IGameField.BOARD_PADDING_X + i * boardBlockSize, IGameField.BOARD_PADDING_Y + j * boardBlockSize);
            }
        }
    }


}

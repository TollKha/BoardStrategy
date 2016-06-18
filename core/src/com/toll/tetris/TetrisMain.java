package com.toll.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.toll.tetris.figures.AFigure;
import com.toll.tetris.figures.FigureO;
import com.toll.tetris.figures.Figurel;

import java.util.Random;

public class TetrisMain extends ApplicationAdapter {
    public final static int GAME_WINDOW_WIDTH = 800;
    public final static int GAME_WINDOW_HEIGHT = 480;

    private OrthographicCamera camera;
    private SpriteBatch boardBatch;

    private int ticksToMove = 5;
    private int currentTicks;

    private static Random rnd = new Random();

    Texture img;

    TetrisBoard board;

    AFigure currFigure;
    FPSLimiter fpsLimiter;

    static TextureRegion trYellow;
    static TextureRegion trBlue;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);

        boardBatch = new SpriteBatch();
        img = new Texture("boardBlock.png");

        final TextureRegion trBoard = new TextureRegion(img, 0, 0, 20, 20);
        trYellow = new TextureRegion(img, 20, 0, 20, 20);
        trBlue = new TextureRegion(img, 40, 0, 20, 20);

        board = new TetrisBoard(trBoard);

        GenerateFigure();

        fpsLimiter = new FPSLimiter(30);
        figureFallTimer = new Timer(figureFallTime, -1, new ITimerCallback() {
            @Override
            public void OnTick(Timer timer) {

            }

            @Override
            public void OnFinished(Timer timer) {
                currentTicks++;

                if (currentTicks >= ticksToMove) {
                    if (board.CheckCollision(currFigure, currFigure.getX(), currFigure.getY() - 1)) {
                        board.AddFigure(currFigure);
                        GenerateFigure();
                    }
                    else {
                        currFigure.MoveDown();
                    }
                    currentTicks = 0;
                }
                timer.Start();
            }
        });
        figureFallTimer.Start();

        TimerManager.AddTimer(figureFallTimer);
    }

    void GenerateFigure() {
        if (true) {
            currFigure = new Figurel(trBlue);
            currFigure.SetPosition(board.GetBoardWidth() / 2 - 1, board.GetBoardHeight() - currFigure.GetHeight());
            return;
        }
        int rand = rnd.nextInt(2);
        switch (rand) {
            case 0:
                currFigure = new FigureO(trYellow);
                break;
            case 1:
                currFigure = new Figurel(trBlue);
                break;
        }
        currFigure.SetPosition(board.GetBoardWidth() / 2 - 1, board.GetBoardHeight() - currFigure.GetHeight());
    }

    @Override
    public void render() {
        GameUpdate(Gdx.graphics.getDeltaTime() * 1000);
        GameRender();

        fpsLimiter.delay();
    }

    private void GameRender() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        boardBatch.setProjectionMatrix(camera.combined);

        boardBatch.begin();
        board.Render(boardBatch);
        currFigure.Render(boardBatch, IGameField.BOARD_PADDING_X + currFigure.getX() * board.boardBlockSize, IGameField.BOARD_PADDING_Y + currFigure.getY() * board.boardBlockSize);
//		boardBatch.draw(trYellow, IGameField.BOARD_PADDING_X + 4 * boardBlockSize, IGameField.BOARD_PADDING_Y + 10 * boardBlockSize);
        boardBatch.end();
    }

    private float figureFallTime = 100f;
    private Timer figureFallTimer;

    private void GameUpdate(float dt) {
        //System.out.println("dt = " + dt);

        TimerManager.Update(dt);

        if (InputManager.IsKeyPressed(Input.Keys.LEFT)) {
            if (!board.CheckCollision(currFigure, currFigure.getX() - 1, currFigure.getY())) {
                currFigure.MoveLeft();
            }
        }

        if (InputManager.IsKeyPressed(Input.Keys.RIGHT)) {
            if (!board.CheckCollision(currFigure, currFigure.getX() + 1, currFigure.getY())) {
                currFigure.MoveRight();
            }
        }

        if (InputManager.IsKeyPressed(Input.Keys.UP)) {
            if (!board.CheckCollision(currFigure, currFigure.getX(), currFigure.getY(), currFigure.GetRotation().GetNextRotation())) {
                currFigure.Rotate();
            }
        }

        if (InputManager.IsKeyPressed(Input.Keys.DOWN)) {
            ticksToMove = 1;
        }

        if (InputManager.IsKeyReleased(Input.Keys.DOWN)) {
            ticksToMove = 5;
        }

        InputManager.Update();
    }
}

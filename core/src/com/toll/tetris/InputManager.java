package com.toll.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by toll on 03.05.16.
 */
public class InputManager {
    final static Timer keyPressDelayTimer = new Timer(100, -1, null);
    private static HashSet<Integer> releasedKeys = new HashSet<Integer>();

    static {
        TimerManager.AddTimer(keyPressDelayTimer);

        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                releasedKeys.add(keycode);
                keyPressDelayTimer.Pause();
                return true;
            }

            @Override
            public boolean keyTyped(char character) {
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return true;
            }

            @Override
            public boolean scrolled(int amount) {
                return true;
            }
        });

    }

    public static boolean IsKeyReleased(int keyCode) {
        if (releasedKeys.contains(keyCode)) {
            return true;
        }

        return false;
    }

    public static boolean IsKeyPressed(int keyCode) {
        if (Gdx.input.isKeyPressed(keyCode)) {
//            System.out.println("IsStarted: " + keyPressDelayTimer.IsStarted());
//            System.out.println("IsFinished: " + keyPressDelayTimer.IsFinished());

            if (!keyPressDelayTimer.IsStarted() || keyPressDelayTimer.IsFinished()) {
                keyPressDelayTimer.Start();
                return true;
            }
        }
        return false;
    }

    public static void Update() {
        releasedKeys.clear();
    }
}

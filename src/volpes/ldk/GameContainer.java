/*
 * Copyright (C) 2012 Lasse Dissing Hansen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package volpes.ldk;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Lasse Dissing Hansen
 * @since 0.1
 */
public class GameContainer {
    /*The list that contains all game parts*/
    private List<GameState> states = new ArrayList<GameState>();
    /* The current part. This is the state or gamemode that runs currently*/
    private GameState currentState;

    private Input input;

    public GameContainer() {
        input = new Input();
    }

    protected void initialize() {
        if (states.size() > 0) {
            gotoState(0);
        } else {
            new LDKException("You must add states to the game, before start!").printStackTrace();
            System.exit(1);
        }
    }

    protected void tick(Settings settings) {
        currentState.update(this,settings);
    }

    protected void render(Settings settings, Render render) {
        currentState.render(this,settings,render);
    }

    /**
     * Changes the current game state
     * @param i The index of the new state
     */
    public void gotoState(int i) {
        try {
            currentState = states.get(i);
            if (!currentState.isInitialized()) {
                currentState.expandedInitalize();
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new LDKException("Unknown state requested!");
        }

    }

    /**
     * @return Index of current gamemode
     */
    public int getCurrentState() {
        return states.indexOf(currentState);
    }

    /**
     * Adds a state to the end of the list
     * @param state
     */
    public void addState(GameState state) {
        states.add(state);
    }

    /**
     * Replaces a state with another
     * @param state
     * @param i
     */
    public void setState(GameState state, int i) {
        states.set(i, state);
    }

    public void removeState(int i) {
        states.remove(i);
    }

    /**
     * @return Current Input wrapper
     */
    public Input getInput() {
        return input;
    }
}
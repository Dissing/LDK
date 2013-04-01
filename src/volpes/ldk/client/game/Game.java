/*
 * Copyright (C) 2013 Lasse Dissing Hansen
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

package volpes.ldk.client.game;

import volpes.ldk.client.event.LEventManager;
import volpes.ldk.client.process.LProcessManager;
import volpes.ldk.client.resources.LResourceManager;
import volpes.ldk.client.scene.LSceneManager;
import volpes.ldk.client.state.LStateManager;

/**
 * @author Lasse Dissing Hansen
 */
public abstract class Game {

    protected LResourceManager resourceManager;
    protected LEventManager eventManager;
    protected LProcessManager processManager;
    protected LStateManager stateManager;
    protected LSceneManager sceneManager;

    public Game() {

    }

    protected void initialise() {
        resourceManager = new LResourceManager("resources.xml");
        eventManager = new LEventManager(this);
        processManager = new LProcessManager();
        stateManager = new LStateManager();
        sceneManager = new LSceneManager();
    }

    protected void cleanup() {
        sceneManager.shutdown();
        stateManager.shutdown();
        processManager.shutdown();
        eventManager.shutdown();
        resourceManager.shutdown();
    }

    public LResourceManager getResourceManager() {
        return resourceManager;
    }

    public LEventManager getEventManager() {
        return eventManager;
    }

    public LProcessManager getProcessManager() {
        return processManager;
    }

    public LStateManager getStateManager() {
        return stateManager;
    }

    public LSceneManager getSceneManager() {
        return sceneManager;
    }

    public abstract void update();

    public abstract void render();
}

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

import volpes.ldk.client.event.EventMgr;
import volpes.ldk.client.process.ProcessMgr;
import volpes.ldk.client.resources.ResourceMgr;
import volpes.ldk.client.scene.SceneMgr;
import volpes.ldk.client.state.StateMgr;

/**
 * @author Lasse Dissing Hansen
 */
public abstract class Game {

    protected ResourceMgr resourceManager;
    protected EventMgr eventManager;
    protected ProcessMgr processManager;
    protected StateMgr stateManager;
    protected SceneMgr sceneManager;

    public Game() {

    }

    protected void initialise() {
        resourceManager = new ResourceMgr("resources.xml");
        eventManager = new EventMgr(this);
        processManager = new ProcessMgr();
        stateManager = new StateMgr();
        sceneManager = new SceneMgr();
    }

    protected void cleanup() {
        sceneManager.shutdown();
        stateManager.shutdown();
        processManager.shutdown();
        eventManager.shutdown();
        resourceManager.shutdown();
    }

    public ResourceMgr getResourceManager() {
        return resourceManager;
    }

    public EventMgr getEventManager() {
        return eventManager;
    }

    public ProcessMgr getProcessManager() {
        return processManager;
    }

    public StateMgr getStateManager() {
        return stateManager;
    }

    public SceneMgr getSceneManager() {
        return sceneManager;
    }

    public abstract void update();

    public abstract void render();
}

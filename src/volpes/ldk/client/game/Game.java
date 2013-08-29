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

import volpes.ldk.client.audio.ALAudioManager;
import volpes.ldk.client.audio.AudioManager;
import volpes.ldk.client.event.EventManager;
import volpes.ldk.client.event.EventMgr;
import volpes.ldk.client.input.Input;
import volpes.ldk.client.process.ProcessManager;
import volpes.ldk.client.process.ProcessMgr;
import volpes.ldk.client.rendering.*;
import volpes.ldk.client.resources.ResourceManager;
import volpes.ldk.client.resources.ResourceMgr;
import volpes.ldk.client.scene.SceneManager;
import volpes.ldk.client.scene.SceneMgr;
import volpes.ldk.client.state.StateManager;
import volpes.ldk.client.state.StateMgr;

/**
 * @author Lasse Dissing Hansen
 */
public abstract class Game {

    boolean shouldClose;

    protected ResourceMgr resourceManager = new ResourceMgr();
    protected EventMgr eventManager = new EventMgr();
    protected ProcessMgr processManager = new ProcessMgr();
    protected StateMgr stateManager = new StateMgr();
    protected SceneMgr sceneManager = new SceneMgr();
    protected Screen screen = new AWTWindow();
    protected AbstractRender renderManager = new Java2DRender();
    protected ALAudioManager audioManager = new ALAudioManager();

    public Game() {
    }

    protected void internalInit() {
        preEngineStart();
        eventManager.initialise(this);
        processManager.initialise();
        stateManager.initialise();
        sceneManager.initialise();
        screen.createScreen();
        renderManager.initialise(screen);
        audioManager.initialise();
        resourceManager.initialise("resources.xml");

        initialise();
    }

    protected void cleanup() {
        audioManager.shutdown();
        renderManager.shutdown();
        screen.destroyScreen();
        sceneManager.shutdown();
        stateManager.shutdown();
        processManager.shutdown();
        eventManager.shutdown();
        resourceManager.shutdown();


    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ProcessManager getProcessManager() {
        return processManager;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public RenderManager getRenderManager() {
        return renderManager;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public Input getInput() {
        return ((AWTWindow)screen).getInput();
    }

    public void close() {
        shouldClose = true;
    }

    public abstract void preEngineStart();

    public abstract void initialise();

    public abstract void update();

    public abstract void render();
}

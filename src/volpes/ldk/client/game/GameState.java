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

import volpes.ldk.client.rendering.Render;

/**
 * Each game state wanting to get called by LDK must extends this
 * @author Lasse Dissing Hansen
 * @since 0.1
 */
public interface GameState {



    /**
     * Called right before the state is used the first time
     */
    public abstract void intialise(GameContainer container);

    /**
     * @return True if the state have been initialised before
     */
    public abstract boolean isInitialized();


    public abstract void cleanup();

	/**
	 * Place all world updates here
	 * Rendering should not be done here 
	 */
	public abstract void update();
	
	/**
	 * Place all rendering here
	 * There should be no side-effects in game here
	 * @param g Render to this graphics
	 */
	public abstract void render(Render g);



    public abstract boolean changeActive(boolean active);

    public abstract boolean isActive(boolean active);

}

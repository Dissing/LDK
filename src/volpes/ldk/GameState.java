package volpes.ldk;

import java.awt.Graphics2D;

/**
 * Each game state wanting to get called by LDK must extends this
 * @author Lasse Dissing Hansen
 */
public abstract class GameState {
	
	
	/* Used to check if its first time game state is used*/
	private boolean initialized = false;
	
	/**
	 * Place all world updates here
	 * Rendering should not be done here 
	 * @param container
	 */
	public abstract void update(GameContainer container);
	
	/**
	 * Place all rendering here
	 * There should be no side-effects in game here
	 * @param container
	 * @param g Render to this graphics
	 */
	public abstract void render(GameContainer container,Graphics2D g);
	
	/**
	 * A extended wrapper around init()
	 */
	protected void expandedInitalize() {
		initialized = true;
		init();
	}
	
	/**
	 * Called right before the state is used the first time
	 */
	public abstract void init();
	
	/**
	 * @return True if the state have been initialised before
	 */
	public boolean isInitialized() {
		return initialized;
	}

}

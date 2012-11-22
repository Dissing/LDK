//============================================================================
// Name        : InputListener
// Author      : Lasse Dissing Hansen
// Version     : 0.1
// Copyright   : (C) 2012 Lasse Dissing Hansen
// Description : 
//============================================================================
package volpes.ldk;

/**
 * A interface for inputlisteners to attach to {@link Input#addListener(InputListener)}
 * @author Lasse Dissing Hansen
 */
public interface InputListener {
	
	public void keyPressed(int key);
	
	public void keyReleased(int key);
	
	public void mouseMoved(int x, int y);
	
	public void mousePressed(int x, int y, int button);
	
	public void mouseReleased(int x, int y, int button);
}

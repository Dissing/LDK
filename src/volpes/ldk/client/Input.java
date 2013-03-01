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
package volpes.ldk.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A simplified wrapper around vanilla input listeners
 * @author Lasse Dissing Hansen
 * @since 0.1
 */
public class Input implements MouseListener, MouseMotionListener, KeyListener {
	
	List<InputListener> listeners = new ArrayList<InputListener>();
	private boolean keys[] = new boolean[65565];
	private boolean buttons[] = new boolean[10];
	
	
	/**
	 * Adds a InputListener to the Input system
	 * This listener will get called at an relevant input event
	 * @param listener The InputListener to add 
	 */
	public void addListener(InputListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes a listener from the Input system
	 * @param listener A reference to the listener
	 */
	public void removeListener(InputListener listener) {
		listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		for (InputListener listener : listeners) {
			listener.keyPressed(e.getKeyCode());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		for (InputListener listener : listeners) {
			listener.keyReleased(e.getKeyCode());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		for (InputListener listener : listeners) {
			listener.mouseMoved(e.getX(), e.getY());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		for (InputListener listener : listeners) {
			listener.mouseMoved(e.getX(), e.getY());
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
		for (InputListener listener : listeners) {
			listener.mousePressed(e.getX(), e.getY(), e.getButton());
		}		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
		for (InputListener listener : listeners) {
			listener.mouseReleased(e.getX(), e.getY(), e.getButton());
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Return true if the key is currently pressed
	 * @param key Use {@link KeyEvent} virtual keys for value
	 * @return True if key is down
	 */
	public boolean isKeyPressed(int key) {
		return keys[key];
	}
	
	/**
	 * Return true if the button is currently pressed
	 * @param button Use {@link MouseEvent} button masks for value
	 * @return True if button is down
	 */
	public boolean isButtonPressed(int button) {
		return buttons[button];
	}
	
	
}

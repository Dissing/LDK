//============================================================================
// Name        : Entity
// Author      : Lasse Dissing Hansen
// Version     : 0.1
// Copyright   : (C) 2012 Lasse Dissing Hansen
// Description : 
//============================================================================
package volpes.ldk;

import java.awt.Graphics;

/**
 * Basic Entity class providing basic interface and position
 * @author Lasse Dissing Hansen
 */
public abstract class Entity {

	private float x;
	
	private float y;
	
	public abstract void render(GameContainer container,Graphics g);

	/**
	 * @return X coordinate as a float
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * @return Y coordinate as a float
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * @return X coordinate rounded as an integer
	 */
	public int getIntX() {
		return (int)x;
	}
	
	/**
	 * @return Y coordinate rounded as an integer
	 */
	public int getIntY() {
		return (int)y;
	}
	
	/**
	 * @param x Sets X coordinate
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * @param y Sets Y coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * @param x Increases X coordinate
	 */
	public void incX(float x) {
		this.x += x;
	}
	
	/**
	 * @param y Increases Y coordinate
	 */
	public void incY(float y) {
		this.y += y;
	}
	
	
}

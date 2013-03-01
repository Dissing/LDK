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

import java.awt.Graphics;

/**
 * Basic Entity class providing basic interface and position
 * @author Lasse Dissing Hansen
 * @since 0.1
 */
public abstract class Entity {
	
	private int x;
	
	private int y;
	
	public abstract void render(GameContainer container,Render g);
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return X coordinate as a float
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return Y coordinate as a float
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param x Sets X coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @param y Sets Y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @param x Increases X coordinate
	 */
	public void incX(int x) {
		this.x += x;
	}
	
	/**
	 * @param y Increases Y coordinate
	 */
	public void incY(int y) {
		this.y += y;
	}
	
	
}

//============================================================================
// Name        : AnimationSheet
// Author      : Lasse Dissing Hansen
// Version     : 0.1
// Copyright   : (C) 2012 Lasse Dissing Hansen
// Description : 
//============================================================================
package volpes.ldk;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationSheet {
	
	public static final int STATICSTATE = 0;
	public static final int TEMPSTATE = 1;
	public static final int ALTSTATE = 2;
	
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private int state;
	private int id1,id2;
	private int time;
	private int currentTime;
	
	/**
	 * Creates a new animation sheet
	 * @param image The image to use
	 * @param tilesize The size of each sprite. Height and width must equal.
	 */
	public AnimationSheet(BufferedImage image, int tilesize) {
		state = 0;
		id1 = 0;
		id2 = 0;
		time = 0;
		currentTime = 0;
		for (int j = 0; j < image.getHeight()/tilesize;j++) {
			for (int i = 0; i < image.getWidth()/tilesize;i++) {
				sprites.add(image.getSubimage(i*tilesize, j*tilesize, tilesize, tilesize));
			}
		}
	}
	
	
	/**
	 * Sets the animation to a static sprite
	 * @param id The number of the sprite
	 */
	public void setStaticState(int id) {
		this.id1 = id;
		this.state = 0;
	}
	
	/**
	 * Temporary sets an new sprite.
	 * After 'time' ticks it change to a static secondID 
	 * @param firstID The temporary sprite 
	 * @param secondID The next sprite
	 * @param time Ticks before changing to secondID;
	 */
	public void setTempState(int firstID, int secondID, int time) {
		this.id1 = firstID;
		this.id2 = secondID;
		this.time = time;
		this.currentTime = 0;
		this.state = TEMPSTATE;
	}
	
	/**
	 * Changes between first and second sprite each 'time' ticks.
	 * Keeps going forever.
	 * @param firstID The first part of the animation
	 * @param secondID The second part of the animation
	 * @param time
	 */
	public void setAltState(int firstID, int secondID, int time) {
		this.id1 = firstID;
		this.id2 = secondID;
		this.time = time;
		this.currentTime = 0;
		this.state = ALTSTATE;
	}
	
	/**
	 * Must be each tick.
	 * Otherwise the animation wil not update.
	 * 
	 */
	public void tick() {
		if (state == TEMPSTATE) {
			currentTime++;
			if (currentTime > time) {
				this.id1 = id2;
				this.currentTime = 0;
				this.state = STATICSTATE;
			}
		} else if (state == ALTSTATE) {
			if (currentTime > time) {
				int temp = id1;
				this.id1 = id2;
				this.id2 = temp;
				this.currentTime = 0;
			}
			currentTime++;
		}
	}
	
	/**
	 * Renders the animation to a specified coordinate.
	 * @param g Graphics
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void render(Graphics g,int x, int y) {
		g.drawImage(sprites.get(id1), x, y,null);
	}
	
	public int getState(){
		return state;
	}
	

}

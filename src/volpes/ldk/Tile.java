//============================================================================
// Name        : Tile
// Author      : Lasse Dissing Hansen
// Version     : 0.1
// Copyright   : (C) 2012 Lasse Dissing Hansen
// Description : 
//============================================================================
package volpes.ldk;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;
	private int id;
	
	public Tile(BufferedImage image, int id) {
		this.image = image;
		this.id = id;
	}

	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getID() {
		return id;
	}
}

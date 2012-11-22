//============================================================================
// Name        : Tile
// Author      : Lasse Dissing Hansen
// Version     : 0.1
// Copyright   : (C) 2012 Lasse Dissing Hansen
// Description : 
//============================================================================
package volpes.ldk;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TileManager {
	
	private int size = 32;
	
	List<Sheet> sheets = new ArrayList<Sheet>();
	
	private int imagesPerSheet = 0;
	
	public TileManager() {
		
	}
	
	public Tile getType(int i,int sheet) throws LDKException{
		try {
			return sheets.get(sheet).get((i-1) - (sheet*64));
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(i);
			throw new LDKException("Failed load type " + i);
		}
		
	}
	
	public Tile getDefault() {
		return sheets.get(0).get(0);
	}
	
	public int getSize() {
		return size;
	}
	
	public void addTileSheet(BufferedImage sheet) {
		sheets.add(new Sheet(sheet));
		if (imagesPerSheet == 0) {
			imagesPerSheet = (int)Math.pow(sheet.getWidth() / size,8);
		} 
	}
	
	private class Sheet {
		List<Tile> types = new ArrayList<Tile>();
		
		public Sheet(BufferedImage image) {
			int w = image.getWidth();
			int h = image.getHeight();
			int index = 0;
			for (int i = 0; i < h; i += 32) {
				for (int j = 0; j < w; j += 32) {
					Tile tile = new Tile(image.getSubimage(j, i, size, size),index+1);
					types.add(tile);
					index++;
				}
			}
		}
		
		public Tile get(int index) {
			if (index >= 0) {
				return types.get(index);
			} else {
				return null;
			}
		}
	}
	
}

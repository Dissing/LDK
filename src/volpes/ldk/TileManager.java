/*Copyright (C) 2012 Lasse Dissing Hansen

Permission is hereby granted, free of charge, to any person obtaining a copy of 
this software and associated documentation files (the "Software"), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package volpes.ldk;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lasse Dissing Hansen
 * @version 0.1
 */
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

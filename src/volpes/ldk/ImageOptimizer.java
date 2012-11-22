//============================================================================
// Name        : ImageOptimizer
// Author      : Lasse Dissing Hansen
// Version     : 0.1
// Copyright   : (C) 2012 Lasse Dissing Hansen
// Description : 
//============================================================================
package volpes.ldk;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

/**
 * Optimises the image to the native colour model
 * Yet to find a device where this matters, but who knows...
 */
public class ImageOptimizer {
	public static BufferedImage optimize(BufferedImage image) {
		GraphicsConfiguration graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		if (image.getColorModel().equals(graphicsConfig.getColorModel())) 
			return image;
		
		BufferedImage newImage = graphicsConfig.createCompatibleImage(image.getWidth(), image.getHeight(),image.getTransparency());
		
		Graphics g2d = newImage.getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return newImage;
			
	}
}

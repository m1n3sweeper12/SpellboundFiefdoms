package com.spellbound.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			File file = new File(path);
			img = ImageIO.read(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	public static BufferedImage applyColorMask(BufferedImage sourceImage, Color maskColor) {
		
		// Create a new in-memory image buffer supporting transparency
        BufferedImage resultImage = new BufferedImage(
            sourceImage.getWidth(), 
            sourceImage.getHeight(), 
            BufferedImage.TYPE_INT_ARGB
        );
        
        // Get the graphics context for the new image buffer
        Graphics2D g2d = resultImage.createGraphics();
        
        // 1. Draw the original image onto our fresh, transparent buffer
        g2d.drawImage(sourceImage, 0, 0, null);
        
        // 2. Restrict future drawing to only overwrite existing pixels
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN));
        
        // 3. Paint the mask color over those existing pixels
        g2d.setColor(maskColor);
        g2d.fillRect(0, 0, sourceImage.getWidth(), sourceImage.getHeight());
        
        // 4. Release graphics resources immediately
        g2d.dispose();
        
        // Return the modified image copy
        return resultImage;
		
	}
	
}

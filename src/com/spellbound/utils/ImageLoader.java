package com.spellbound.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
	
	public static BufferedImage rotateImage(BufferedImage img, double degrees) {
        // Convert degrees to radians
        double rads = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        
        // Calculate new dimensions to prevent clipping
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        // Create a new blank canvas with transparency support
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        
        // Use an AffineTransform to manage the rotation steps
        AffineTransform at = new AffineTransform();
        
        // 2. Translate back to center the original image bounds inside the new larger canvas
        at.translate((newWidth - w) / 2.0, (newHeight - h) / 2.0);
        
        // 1. Rotate around the center point of the original image
        at.rotate(rads, w / 2.0, h / 2.0);
        
        // Apply the transformation matrix and draw
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
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

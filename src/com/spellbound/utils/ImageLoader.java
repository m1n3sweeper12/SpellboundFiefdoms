package com.spellbound.utils;

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
	
}

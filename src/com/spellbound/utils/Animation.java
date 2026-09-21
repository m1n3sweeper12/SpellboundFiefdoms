package com.spellbound.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {
	
	private int x, y;
	private int width, height;
	
	private BufferedImage[] imgs;
	private BufferedImage currentImg;
	
	public Animation(int x, int y, int width, int height, BufferedImage... imgs) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imgs = imgs;
	}
	
	public void runAnimation() {
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(currentImg, x, y, width, height, null);
	}
	
}

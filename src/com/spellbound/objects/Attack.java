package com.spellbound.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.spellbound.utils.Colors;

public class Attack {
	
	private Rectangle bounds;
	private int power;
	
	public Attack(int x, int y, int width, int height, int power) {
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(Colors.black);
		g.fill(bounds);
	}
	
}

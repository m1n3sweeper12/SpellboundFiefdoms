package com.spellbound.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.spellbound.states.Game;
import com.spellbound.utils.Colors;

public class Tile {
	
	private Rectangle bounds;
	private boolean isSolid;
	private BufferedImage img;
	private int id;
	
	// TEMP
	private Color c;
	
	public Tile(int x, int y, int width, int height, int id, boolean isSolid, BufferedImage img) {
		this.bounds = new Rectangle(x, y, width, height);
		this.id = id;
		this.isSolid = isSolid;
		// if image doesn't load, default to colors
		if(img == null) {
			switch(id) {
			case 0:
				c = Colors.green.brighter().brighter();
				break;
			case 1:
				c = Colors.black.brighter().brighter();
				break;
			default:
				c = Colors.white;
			}
			
		} else {
			this.img = img;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		if(img != null)
			g.drawImage(img, bounds.x, bounds.y, Game.TILE_SIZE, Game.TILE_SIZE, null);
		else {
			g.setColor(c);
			g.fill(bounds);
			g.setColor(c.darker().darker());
			g.draw(bounds);
		}
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}

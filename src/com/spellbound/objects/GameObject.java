package com.spellbound.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/*
 * GameObject class is an abstract class meant to model any interactive object in the world,
 * including the player, enemies, NPCs, interactive items, etc.
 */

public abstract class GameObject {
	
	protected Rectangle bounds;
	protected int id; // used to track object types for collisions
	// id list:
	// 0 -> player
	// 1 -> enemy/hostile
	// 2 -> NPC/passive
	// 3 -> item
	
	//TEMP, could be used for bounds debugging later
	Color c;
	
	// TEMP: need to add animations/images
	public GameObject(float x, float y, int width, int height, int id, Color c) {
		this.bounds = new Rectangle((int)x, (int)y, width, height);
		this.c = c;
	}
	
	public abstract void tick();
	
	public void render(Graphics2D g) {
		g.setColor(c);
		g.fill(bounds);
		g.setColor(c.darker().darker());
		g.draw(bounds);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public int[] getPos() {
		int[] pos = {bounds.x, bounds.y};
		return pos;
	}
	
	public int getX() {
		return bounds.x;
	}
	
	public int getY() {
		return bounds.y;
	}
	
	public int getID() {
		return id;
	}
	
}

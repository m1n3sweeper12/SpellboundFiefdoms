package com.spellbound.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/*
 * GameObject class is an abstract class meant to model any interactive object in the world,
 * including the player, enemies, NPCs, interactive items, etc.
 */

public abstract class GameObject {
	
	private float x, y;
	private Rectangle bounds;
	private int id; // used to track object types for collisions
	// id list:
	// 0 -> player
	// 1 -> enemy/hostile
	// 2 -> NPC/passive
	// 3 -> item
	
	//TEMP, could be used for bounds debugging later
	Color c;
	
	// TEMP: need to add animations/images
	public GameObject(float x, float y, int width, int height, int id, Color c) {
		this.x = x;
		this.y = y;
		this.bounds = new Rectangle((int)x, (int)y, width, height);
		this.c = c;
	}
	
	public abstract void tick();
	
	public void render(Graphics2D g) {
		g.setColor(c);
		g.fill(bounds);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public float[] getPos() {
		float[] pos = {x, y};
		return pos;
	}
	
	public int getID() {
		return id;
	}
	
}

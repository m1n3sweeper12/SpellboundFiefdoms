package com.spellbound.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.tiles.Tile;
import com.spellbound.utils.Colors;

import javafx.scene.shape.Circle;

/*
 * GameObject class is an abstract class meant to model any interactive object in the world,
 * including the player, enemies, NPCs, interactive items, etc.
 */

public abstract class GameObject {
	
	protected float x, y;
	
	protected Rectangle bounds;
	protected Rectangle topBounds, bottomBounds, leftBounds, rightBounds;
	
	protected Circle strikeRad;
	
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
		this.x = x;
		this.y = y;
		this.bounds = new Rectangle((int)x, (int)y, width, height);
		this.topBounds = new Rectangle((int)(x + 5), (int)(y), width - 10, 5);
		this.bottomBounds = new Rectangle((int)(x + 5), (int)(y + height - 5), width - 10, 5);
		this.leftBounds = new Rectangle((int)(x), (int)(y + 5), 5, height - 10);
		this.rightBounds = new Rectangle((int)(x + width - 5), (int)(y + 5), 5, height - 10);
		this.c = c;
	}
	
	public abstract void tick(Map m);
	
	public void render(Graphics2D g) {
		g.setColor(c);
		g.fill(bounds);
		g.setColor(c.darker().darker());
		g.draw(bounds);
		if(Game.debugMode) {
			g.setColor(Colors.red);
			g.draw(topBounds);
			g.setColor(Colors.orange);
			g.draw(bottomBounds);
			g.setColor(Colors.purple);
			g.draw(leftBounds);
			g.setColor(Colors.yellow);
			g.draw(rightBounds);
		}
	}
	
	protected void move() {
		this.bounds.x = (int)this.x;
		this.bounds.y = (int)this.y;
		this.topBounds.x = (int)this.x + 5;
		this.topBounds.y = (int)this.y;
		this.bottomBounds.x = (int)this.x + 5;
		this.bottomBounds.y = (int)this.y + this.bounds.height - 5;
		this.leftBounds.x = (int)this.x;
		this.leftBounds.y = (int)this.y + 5;
		this.rightBounds.x = (int)this.x + this.bounds.width - 5;
		this.rightBounds.y = (int)this.y + 5;
	}
	
	protected void tileCollide(Map m) {
		
		for(Tile t : m.getTiles()) {
			if(topBounds.intersects(t.getBounds()) && t.isSolid()) {
				this.y = t.getBounds().y + Game.TILE_SIZE;
			}
			if(bottomBounds.intersects(t.getBounds()) && t.isSolid()) {
				this.y = t.getBounds().y - Game.TILE_SIZE;
			}
			if(leftBounds.intersects(t.getBounds()) && t.isSolid()) {
				//System.out.println("got here");
				this.x = t.getBounds().x + Game.TILE_SIZE;
			}
			if(rightBounds.intersects(t.getBounds()) && t.isSolid()) {
				this.x = t.getBounds().x - Game.TILE_SIZE;
			}
		}
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
	
	public int getCenterX() {
		return bounds.x + bounds.width/2;
	}
	
	public int getY() {
		return bounds.y;
	}
	
	public int getCenterY() {
		return bounds.y + bounds.height/2;
	}
	
	public int getID() {
		return id;
	}
	
}

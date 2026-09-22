package com.spellbound.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.tiles.Tile;
import com.spellbound.utils.Animation;
import com.spellbound.utils.Colors;

/*
 * GameObject class is an abstract class meant to model any interactive object in the world,
 * including the player, enemies, NPCs, interactive items, etc.
 */

public abstract class GameObject {
	
	protected float x, y;
	
	protected Rectangle bounds;
	protected Rectangle topBounds, bottomBounds, leftBounds, rightBounds;
	protected Rectangle strikeArea;
	protected int strikeDist;
	protected Rectangle damageArea;
	protected int direction;
	// direction list:
	// 0 -> east
	// 1 -> south east
	// 2 -> south
	// 3 -> south west
	// 4 -> west
	// 5 -> north west
	// 6 -> north
	// 7 -> north east
	
	protected int power;
	protected boolean canAttack, attacking;
	protected int hp;
	
	protected Animation currAnim;
	
	protected int id; // used to track object types for collisions
	// id list:
	// 0 -> player
	// 1 -> enemy/hostile
	// 2 -> NPC/passive
	// 3 -> item
	
	//TEMP, could be used for bounds debugging later
	Color c;
	
	// TEMP: need to add animations/images
	public GameObject(float x, float y, int width, int height, int id, Color c, int damageAreaRad, int strikeAreaRad, int power, int hp) {
		this.x = x;
		this.y = y;
		// for attack/damage collisions
		this.bounds = new Rectangle((int)x, (int)y, width, height);
		// for tile collisions
		this.topBounds = new Rectangle((int)(x + 5), (int)(y), width - 10, 5);
		this.bottomBounds = new Rectangle((int)(x + 5), (int)(y + height - 5), width - 10, 5);
		this.leftBounds = new Rectangle((int)(x), (int)(y + 5), 5, height - 10);
		this.rightBounds = new Rectangle((int)(x + width - 5), (int)(y + 5), 5, height - 10);
		// for debugging
		this.c = c;
		// creates a circle representing the area in which the object can take damage
		this.damageArea = new Rectangle((int)x - damageAreaRad, (int)y - damageAreaRad, damageAreaRad*2, damageAreaRad*2);
		// creates a circle representing the are in which the object can attack
		this.strikeArea = new Rectangle((int)x - strikeAreaRad, (int)y - strikeAreaRad, strikeAreaRad*2, strikeAreaRad*2);
		this.strikeDist = strikeAreaRad;
		this.power = power;
		this.canAttack = true;
		this.attacking = false;
		// tracks health points
		this.hp = hp;
	}
	
	public abstract void tick(Map m);
	
	public void render(Graphics2D g) {
		// TEMP
		g.setColor(c);
		g.fill(bounds);
		g.setColor(c.darker().darker());
		g.draw(bounds);
		
		g.drawImage(currAnim.getCurrentFrame(), (int)x, (int)y, Game.TILE_SIZE, Game.TILE_SIZE, null);
		
		if(Game.debugMode) {
			// draw collision bounds
			g.setColor(Colors.red);
			g.draw(topBounds);
			g.setColor(Colors.orange);
			g.draw(bottomBounds);
			g.setColor(Colors.purple);
			g.draw(leftBounds);
			g.setColor(Colors.yellow);
			g.draw(rightBounds);
			
			// draw damageArea & strikeArea
			g.setColor(Colors.blue);
			g.draw(damageArea);
			g.setColor(Colors.black);
			g.draw(strikeArea);
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
		this.damageArea.x = ((int)this.getCenterX() - damageArea.width/2);
		this.damageArea.y = ((int)this.getCenterY() - damageArea.height/2);
		this.strikeArea.x = (int)this.getCenterX() - strikeArea.width/2;
		this.strikeArea.y = (int)this.getCenterY() - strikeArea.height/2;
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
	
	public Rectangle getDamageArea() {
		return damageArea;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	public void damage(int amt) {
		this.hp -= amt;
	}
	
	public void heal(int amt) {
		this.hp += amt;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getStrikeDist() {
		return strikeDist;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
}

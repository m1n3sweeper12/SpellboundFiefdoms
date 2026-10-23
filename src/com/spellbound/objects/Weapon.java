package com.spellbound.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.spellbound.inventory.Item;
import com.spellbound.states.Game;

public abstract class Weapon extends Item {
	
	private int power;
	private int reach; // might need later
	private int direction;
	private Rectangle bounds;
	
	private BufferedImage img;
	private GameObject o; // stores object that has weapon
	
	public Weapon(String name, int power, int reach, GameObject o, int width, int height, BufferedImage img) {
		super(name, "weapon", 1, 0, 0, img);
		if(o != null) {
			this.bounds = new Rectangle(o.getCenterX(), o.getCenterY(), width, height);
			this.setX(o.getCenterX());
			this.setY(o.getCenterY());
		} else {
			this.bounds = new Rectangle(0, 0, width, height);
		}
		this.power = power;
		this.reach = reach;
		this.img = img;
		
	}
	
	public void tick() {
		bounds.x = o.getCenterX();
		bounds.y = o.getCenterY();
		this.setX(o.getCenterX());
		this.setY(o.getCenterY());
	}
	
	public void render(Graphics2D g, GameObject o) {
		switch(direction) {
		case 0: // east, no rotation
			g.drawImage(img, o.getCenterX(), o.getCenterY(), bounds.width, bounds.height, null);
			break;
		case 1: // south east
			g.rotate(7*Math.PI/4, bounds.width/2, bounds.height/2);
			g.drawImage(img, o.getCenterX(), o.getCenterY(), bounds.width, bounds.height, null);
			g.rotate(-7*Math.PI/4, bounds.width/2, bounds.height/2);
			break;
		case 2: // south
			g.rotate(3*Math.PI/2, bounds.width/2, bounds.height/2);
			g.drawImage(img, o.getCenterX(), o.getCenterY(), bounds.width, bounds.height, null);
			g.rotate(-3*Math.PI/2, bounds.width/2, bounds.height/2);
			break;
		case 3: // south west
			g.rotate(5*Math.PI/4, bounds.width/2, bounds.height/2);
			g.drawImage(img, o.getCenterX(), o.getCenterY(), bounds.width, bounds.height, null);
			g.rotate(-5*Math.PI/4, bounds.width/2, bounds.height/2);
			break;
		case 4: // west
			g.rotate(Math.PI, bounds.width/2, bounds.height/2);
			g.drawImage(img, o.getCenterX(), o.getCenterY(), bounds.width, bounds.height, null);
			g.rotate(-Math.PI, bounds.width/2, bounds.height/2);
			break;
		case 5: // north west
			g.rotate(3*Math.PI/4, bounds.width/2, bounds.height/2);
			g.drawImage(img, o.getCenterX(), o.getCenterY(), bounds.width, bounds.height, null);
			g.rotate(-3*Math.PI/4, bounds.width/2, bounds.height/2);
			break;
		case 6: // north
			g.rotate(Math.PI/2, bounds.width/2, bounds.height/2);
			g.drawImage(img, o.getCenterX(), o.getCenterY(), bounds.width, bounds.height, null);
			g.rotate(-Math.PI/2, bounds.width/2, bounds.height/2);
			break;
		case 7: // north east
			g.rotate(Math.PI/4, bounds.width/2, bounds.height/2);
			g.drawImage(img, o.getCenterX(), o.getCenterY(), bounds.width, bounds.height, null);
			g.rotate(-Math.PI/4, bounds.width/2, bounds.height/2);
			break;
		default:
			break;
		}
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
}

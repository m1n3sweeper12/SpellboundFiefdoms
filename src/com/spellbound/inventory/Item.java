package com.spellbound.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.spellbound.states.Game;

public class Item {
	
	private String name;
	private int quantity;
	private int x, y;
	private BufferedImage img;
	public static final int MAX_QUANTITY = 99;
	
	public Item(String name, int quantity, int x, int y, BufferedImage img) {
		this.name = name;
		this.quantity = quantity;
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(img, x, y, Game.TILE_SIZE/2, Game.TILE_SIZE/2, null);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}

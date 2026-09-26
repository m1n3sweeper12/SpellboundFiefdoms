package com.spellbound.inventory;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.spellbound.utils.Colors;

public class Inventory {
	
	public ArrayList<Item> items;
	public int slots;
	
	public Inventory(int slots) {
		this.slots = slots;
		items = new ArrayList<Item>(slots);
	}
	
	public void addItem(Item item, int slot) {
		items.add(slot, item);
	}
	
	public void removeItem(int slot) {
		items.remove(slot);
	}
	
	public void removeItem(Item item) {
		if(items.contains(item)) {
			items.remove(item);
		}
	}
	
	// only used for player inventory
	public void render(Graphics2D g) {
		// draw background
		g.setColor(Colors.black.brighter());
		g.fillRect(15, 100, 68, 68*5);
		
		// draw boxes
		
		g.fillRect(15+2, 100+2+68*1, 64, 64);
		g.fillRect(15+2, 100+2+68*2, 64, 64);
		g.fillRect(15+2, 100+2+68*3, 64, 64);
		g.fillRect(15+2, 100+2+68*4, 64, 64);
		
		// draw items
		for(int i = 0; i < 5; i++) {
			g.setColor(Colors.blue.brighter().brighter());
			g.fillRect(15+2, 100+2+68*i, 64, 64);
			if(items.size() > i) {
				Item item = items.get(i);
				item.setX(15+2+16);
				item.setY(100+2+68*i+16);
				item.render(g);
				if(item.getQuantity() > 1) {
					System.out.println("got here");
					g.setColor(Colors.black);
					g.setFont(new Font("Sansserif", Font.BOLD, 16));
					g.drawString(item.getQuantity() + "", 15+16+32, 100+2+68*i+16+4);
				}
			}
		}
		
		// draw highlighted item
		g.setColor(Colors.yellow);
		g.setStroke(new BasicStroke(2));
		g.drawRect(15, 100, 68, 68);
	}
	
}

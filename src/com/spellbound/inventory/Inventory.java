package com.spellbound.inventory;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.spellbound.utils.Colors;

public class Inventory {
	
	private ArrayList<Item> items;
	private int slots;
	private int highSlot;
	
	public Inventory(int slots) {
		this.slots = slots;
		items = new ArrayList<Item>(slots);
		highSlot = 0;
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
		g.fillRect(15, 100, 68, 68*8);
		
		// draw boxes
		
		for(int i = 0; i < 8; i++) {
			g.fillRect(15+2, 100+2+68*i, 64, 64);
		}
		
		// draw items
		for(int i = 0; i < 8; i++) {
			g.setColor(Colors.blue.brighter().brighter());
			g.fillRect(15+2, 100+2+68*i, 64, 64);
			if(items.size() > i) {
				Item item = items.get(i);
				Item newItem = new Item(item.getName(), item.getType(), item.getQuantity(),
						15+2+16, 100+2+68*i+16, item.getImage());
				newItem.render(g);
				if(newItem.getQuantity() > 1) {
					g.setColor(Colors.black);
					g.setFont(new Font("Sansserif", Font.BOLD, 16));
					g.drawString(newItem.getQuantity() + "", 15+16+32, 100+2+68*i+16+4);
				}
			}
		}
		
		// draw highlighted item
		g.setColor(Colors.yellow);
		g.setStroke(new BasicStroke(2));
		g.drawRect(15, 100 + 68*highSlot, 68, 68);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public int getSlots() {
		return slots;
	}
	
	public int getHighlightedSlot() {
		return highSlot;
	}
	
	public void setHighlightedSlot(int highSlot) {
		this.highSlot = highSlot;
	}
	
}

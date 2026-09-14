package com.spellbound.tiles;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.spellbound.states.Game;

public class Map {
	
	private ArrayList<Tile> tiles;
	
	public Map() {
		this.tiles = new ArrayList<>();
	}
	
	public void tick() {
		for(Tile t : tiles) {
			t.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for(Tile t : tiles) {
			t.render(g);
		}
	}
	
	public void loadMapFile(String path) {
		try {
			File file = new File(path);
			
			Scanner in = new Scanner(file);
			
			int width = in.nextInt();
			int height = in.nextInt();
			
			//System.out.println("width: " + width + " | height: " + height);
			
			while(in.hasNext()) {
				int layer = in.nextInt();
				System.out.println(layer);
				switch(layer) {
				case 0: // tiles layer
					//System.out.println("got here");
					for(int y = 0; y < height; y++) {
						for(int x = 0; x < width; x++) {
							int tileID = in.nextInt();
							//System.out.println(layer + " \t| " + x + " \t| " + y + " \t| " + tileID);
							loadTile(x, y, tileID);
						}
					}
					break;
				case 1: // objects layer
					// TEMP
					for(int y = 0; y < height; y++) {
						for(int x = 0; x < width; x++) {
							in.nextInt();
						}
					}
					// TODO: load objects here
					break;
				}
			}
			
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadTile(int x, int y, int tileID) {
		Tile t;
		switch(tileID) {
		case 0:
			t = new Tile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, tileID, false, null);
			break;
		case 1:
			t = new Tile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, tileID, /*true*/false, null);
			break;
		default:
			t = new Tile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, tileID, false, null);
			break;
		}
		
		tiles.add(t);
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
}

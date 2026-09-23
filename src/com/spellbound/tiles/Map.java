package com.spellbound.tiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.spellbound.states.Game;
import com.spellbound.utils.SpriteHandler;

public class Map {
	
	private ArrayList<Tile> tiles;
	
	public static int MAP_WIDTH, MAP_HEIGHT;
	
	public Map() {
		this.tiles = new ArrayList<>();
	}
	
	/*private void loadTileArray(BufferedImage[] arr, BufferedImage img, int width, int height) {
		arr = new BufferedImage[width*height];
		
		int rows = img.getHeight()/height;
		int cols = img.getWidth()/width;
		int index = 0;
		
		for(int y = 0; y < rows; y++) {
			for(int x = 0; x < cols; x++) {
				BufferedImage sub = img.getSubimage(x, y, width, height);
				arr[index] = sub;
				index++;
			}
		}
	}*/
	
	public void loadMapFile(String path) {
		try {
			File file = new File(path);
			
			Scanner in = new Scanner(file);
			
			int width = in.nextInt();
			int height = in.nextInt();
			
			MAP_WIDTH = width*Game.TILE_SIZE;
			MAP_HEIGHT = height*Game.TILE_SIZE;
			
			while(in.hasNext()) {
				int layer = in.nextInt();
				switch(layer) {
				case 0: // path tiles layer
					for(int y = 0; y < height; y++) {
						for(int x = 0; x < width; x++) {
							int tileID = in.nextInt();
							loadTile(x, y, 0, tileID);
						}
					}
					break;
				case 1: // cliff tiles layer
					for(int y = 0; y < height; y++) {
						for(int x = 0; x < width; x++) {
							int tileID = in.nextInt();
							loadTile(x, y, 1, tileID);
						}
					}
					break;
				}
			}
			
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(MAP_WIDTH + " | " + MAP_HEIGHT);
	}
	
	private void loadTile(int x, int y, int layer, int tileID) {
		
		if(layer == 1 && tileID == 0)
			return;
		
		Tile t;
		switch(tileID) {
		case 0: // default tile
			t = new Tile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, tileID, false, SpriteHandler.path_tiles[47]);
			break;
		case 1: // solid wall
			t = new Tile(x*Game.TILE_SIZE, y*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, tileID, true, SpriteHandler.cliff_tiles[49]);
			break;
		default:
			t = null;
			break;
		}
		
		if(t != null)
			tiles.add(t);
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
}

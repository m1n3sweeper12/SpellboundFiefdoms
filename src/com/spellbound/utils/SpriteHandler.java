package com.spellbound.utils;

import java.awt.image.BufferedImage;

public class SpriteHandler {
	
	public static final int SPRITE_SIZE = 32, TILE_SIZE = 16;
	
	// PLAYER ANIMATIONS
	
	public static Animation player_idleU, player_idleD, player_idleL, player_idleR;
	public static Animation player_runU, player_runD, player_runL, player_runR;
	public static Animation player_walkU, player_walkD, player_walkL, player_walkR;
	public static Animation player_punchU, player_punchD, player_punchL, player_punchR;
	public static Animation player_dieU, player_dieD, player_dieL, player_dieR;
	
	// ENEMY ANIMATIONS
	
	public static Animation enemy_idleU, enemy_idleD, enemy_idleL, enemy_idleR;
	public static Animation enemy_runU, enemy_runD, enemy_runL, enemy_runR;
	public static Animation enemy_walkU, enemy_walkD, enemy_walkL, enemy_walkR;
	public static Animation enemy_punchU, enemy_punchD, enemy_punchL, enemy_punchR;
	public static Animation enemy_dieU, enemy_dieD, enemy_dieL, enemy_dieR;
	
	// TILE IMAGES
	
	public static BufferedImage[] path_tiles, cliff_tiles;
	
	// NPC ANIMATIONS
	public static Animation serf1_idleU, serf1_idleD, serf1_idleL, serf1_idleR;
	public static Animation serf1_walkU, serf1_walkD, serf1_walkL, serf1_walkR;
	
	public static void loadAssets() {
		
		//////////////////
		///   PLAYER   ///
		//////////////////
		
		// TODO: add player character customization
		BufferedImage player_idle = ImageLoader.loadImage("res/sprites/player/barbarian-idle.png");
		BufferedImage player_run = ImageLoader.loadImage("res/sprites/player/barbarian-run.png");
		BufferedImage player_walk = ImageLoader.loadImage("res/sprites/player/barbarian-walk.png");
		BufferedImage player_punch = ImageLoader.loadImage("res/sprites/player/barbarian-punch.png");
		BufferedImage player_die = ImageLoader.loadImage("res/sprites/player/barbarian-die.png");
		
		// PLAYER IDLE //
		player_idleR = new Animation(6, player_idle.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_idleU = new Animation(6, player_idle.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_idleL = new Animation(6, player_idle.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_idleD = new Animation(6, player_idle.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_idle.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		
		// PLAYER WALK //
		player_walkR = new Animation(6, player_walk.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(4*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(5*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_walkU = new Animation(6, player_walk.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(4*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(5*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_walkL = new Animation(6, player_walk.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(4*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(5*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_walkD = new Animation(6, player_walk.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(4*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_walk.getSubimage(5*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		
		// PLAYER RUN //
		player_runR = new Animation(8,	player_run.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(4*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(5*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_runU = new Animation(8,	player_run.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(4*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(5*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_runL = new Animation(8,	player_run.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(4*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(5*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_runD = new Animation(8,	player_run.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(4*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(5*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		
		/////////////////
		///   ENEMY   ///
		/////////////////
		
		BufferedImage enemy_idle = ImageLoader.loadImage("res/sprites/enemy/barbarian-idle.png");
		BufferedImage enemy_walk = ImageLoader.loadImage("res/sprites/enemy/barbarian-walk.png");
		BufferedImage enemy_run = ImageLoader.loadImage("res/sprites/enemy/barbarian-run.png");
		
		// ENEMY IDLE //
		enemy_idleR = new Animation(6, enemy_idle.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_idleU = new Animation(6, enemy_idle.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_idleL = new Animation(6, enemy_idle.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_idleD = new Animation(6, enemy_idle.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_idle.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
						
		// ENEMY WALK //
		enemy_walkR = new Animation(6, enemy_walk.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(4*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(5*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_walkU = new Animation(6, enemy_walk.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(4*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(5*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_walkL = new Animation(6, enemy_walk.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(4*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(5*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_walkD = new Animation(6, enemy_walk.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(4*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_walk.getSubimage(5*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
						
		// ENEMY RUN //
		enemy_runR = new Animation(8,	enemy_run.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(4*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(5*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_runU = new Animation(8,	enemy_run.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(4*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(5*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_runL = new Animation(8,	enemy_run.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(4*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(5*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		enemy_runD = new Animation(8,	enemy_run.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(4*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										enemy_run.getSubimage(5*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		
		/////////////////
		///   TILES   ///
		/////////////////
		
		BufferedImage tileset_path = ImageLoader.loadImage("res/sprites/tiles/tileset_path.png");
		BufferedImage tileset_cliff = ImageLoader.loadImage("res/sprites/tiles/tileset_cliff.png");
		
		path_tiles = new BufferedImage[(tileset_path.getWidth()/TILE_SIZE)*(tileset_path.getHeight()/TILE_SIZE)];
		cliff_tiles = new BufferedImage[(tileset_cliff.getWidth()/TILE_SIZE)*(tileset_cliff.getHeight()/TILE_SIZE)];
		
		path_tiles = loadTileset(tileset_path);
		cliff_tiles = loadTileset(tileset_cliff);
		
		////////////////
		///   NPCS   ///
		////////////////
		
		BufferedImage serf1_idle = ImageLoader.loadImage("res/sprites/npcs/serf1/idle.png");
		BufferedImage serf1_walk = ImageLoader.loadImage("res/sprites/npcs/serf1/walk.png");
		
		serf1_idleR = new Animation(6,	serf1_idle.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		serf1_idleU = new Animation(6,	serf1_idle.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		serf1_idleL = new Animation(6,	serf1_idle.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		serf1_idleD = new Animation(6,	serf1_idle.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_idle.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		
		serf1_walkR = new Animation(6,	serf1_walk.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(4*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(5*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		serf1_walkU = new Animation(6,	serf1_walk.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(4*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(5*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		serf1_walkL = new Animation(6,	serf1_walk.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(4*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(5*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		serf1_walkD = new Animation(6,	serf1_walk.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(4*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										serf1_walk.getSubimage(5*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		
	}
	
	private static BufferedImage[] loadTileset(BufferedImage img) {
		int width = img.getWidth()/TILE_SIZE;
		int height = img.getHeight()/TILE_SIZE;
		
		BufferedImage[] arr = new BufferedImage[width*height];
		
		int index = 0;
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				arr[index] = img.getSubimage(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				index++;
			}
		}
		
		return arr;
	}
	
}

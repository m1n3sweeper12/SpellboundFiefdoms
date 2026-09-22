package com.spellbound.utils;

import java.awt.image.BufferedImage;

public class SpriteHandler {
	
	public static final int SPRITE_SIZE = 32;
	
	public static Animation player_idleU, player_idleD, player_idleL, player_idleR;
	public static Animation player_runU, player_runD, player_runL, player_runR;
	public static Animation player_walkU, player_walkD, player_walkL, player_walkR;
	public static Animation player_punchU, player_punchD, player_punchL, player_punchR;
	public static Animation player_dieU, player_dieD, player_dieL, player_dieR;
	
	public static void loadAssets() {
		// TODO: add player character customization
		BufferedImage player_idle = ImageLoader.loadImage("res/sprites/player/BarbarianIdle.png");
		BufferedImage player_run = ImageLoader.loadImage("res/sprites/player/BarbarianRun.png");
		BufferedImage player_walk = ImageLoader.loadImage("res/sprites/player/BarbarianWalk.png");
		BufferedImage player_punch = ImageLoader.loadImage("res/sprites/player/BarbarianPunch.png");
		BufferedImage player_Die = ImageLoader.loadImage("res/sprites/player/BarbarianDie.png");
		
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
		player_runR = new Animation(6,	player_run.getSubimage(0*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(1*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(2*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(3*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(4*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(5*SPRITE_SIZE, 0*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_runU = new Animation(6,	player_run.getSubimage(0*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(1*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(2*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(3*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(4*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(5*SPRITE_SIZE, 1*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_runL = new Animation(6,	player_run.getSubimage(0*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(1*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(2*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(3*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(4*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(5*SPRITE_SIZE, 2*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		player_runD = new Animation(6,	player_run.getSubimage(0*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(1*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(2*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(3*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(4*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE),
										player_run.getSubimage(5*SPRITE_SIZE, 3*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		
	}
	
}

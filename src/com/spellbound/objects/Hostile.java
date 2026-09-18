package com.spellbound.objects;

import java.awt.Rectangle;
import java.util.Random;

import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;

public abstract class Hostile extends GameObject {
	
	protected boolean playerSeen;
	protected MOBSTATES state;
	protected Rectangle moveArea;
	private int moveTimer, speed;
	
	private Random r;
	
	public Hostile(float x, float y, int width, int height, int moveDist, int speed) {
		super(x, y, width, height, 1, Colors.red);
		this.state = MOBSTATES.Roam;
		this.moveArea = new Rectangle((int)(x - moveDist), (int)(y - moveDist), moveDist*2, moveDist*2);
		this.speed = speed;
		
		this.r = new Random((long)(x + y));
	}

	@Override
	public void tick(Map m) {
		switch(state) {
		case Roam:
			roam();
			break;
		case Chase:
			chase();
			break;
		case Attack:
			attack();
			break;
		}
		
		this.move();
		
		this.tileCollide(m);
	}
	
	public void roam() {
		int xDir = 0, yDir = 0;
		if(moveTimer <= 0) {
			int[] nextPoint = new int[2];
			
			nextPoint[0] = r.nextInt(moveArea.x, moveArea.x + moveArea.width);
			nextPoint[1] = r.nextInt(moveArea.y, moveArea.y + moveArea.height);
			
			moveTimer = r.nextInt(200, 5000);
		} else {
			x += xDir*speed;
			y += yDir*speed;
			
			moveTimer--;
		}
		
		
	}
	
	public void chase() {
		
	}
	
	public void attack() {
		
	}
	
	public boolean isPlayerSeen() {
		return playerSeen;
	}
	
	public void setPlayerSeen(boolean playerSeen) {
		this.playerSeen = playerSeen;
	}
	
	public MOBSTATES getState() {
		return state;
	}
	
	public void setState(MOBSTATES state) {
		this.state = state;
	}

}

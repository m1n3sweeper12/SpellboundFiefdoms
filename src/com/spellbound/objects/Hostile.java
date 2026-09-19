package com.spellbound.objects;

import java.awt.Rectangle;
import java.util.Random;

import com.spellbound.tiles.Map;
import com.spellbound.utils.Circle;
import com.spellbound.utils.Colors;

public abstract class Hostile extends GameObject {
	
	protected boolean playerSeen;
	protected MOBSTATES state;
	
	// roaming variables
	protected Rectangle roamArea; // tracks area in which hostile can roam
	private int moveTimer, roamSpeed;	// moveTimer randomly selects time between movements
										// roamSpeed tracks hostile speed while roaming
	private int xDir, yDir, xDist, yDist; // Dir tracks direction of movement, dist tracks distance of movement
	
	private Random r;
	
	protected Circle strikeArea;
	
	public Hostile(float x, float y, int width, int height, int moveDist, int roamSpeed, int damageAreaRad, int strikeAreaRad, int power, int hp) {
		super(x, y, width, height, 1, Colors.red, damageAreaRad, strikeAreaRad, power, hp);
		this.state = MOBSTATES.Roam;
		this.roamArea = new Rectangle((int)(x - moveDist), (int)(y - moveDist), moveDist*2, moveDist*2);
		this.roamSpeed = roamSpeed;
		this.strikeArea = new Circle(x + width/2, y + height/2, strikeAreaRad);
		
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
		if(moveTimer <= 0) {
			xDir = r.nextInt(2);
			yDir = r.nextInt(2);
			
			if(xDir == 0) {
				xDir = -1;
			}
			
			if(yDir == 0) {
				yDir = -1;
			}
			
			xDist = r.nextInt(roamArea.width);
			yDist = r.nextInt(roamArea.height);
			
			//moveTimer = r.nextInt(200, 1000);
			moveTimer = 200;
		} else {
			moveTimer--;
		}
		
		if(xDist > 0) {
			this.x += xDir*roamSpeed;
			xDist -= roamSpeed;
		}
		if(yDist > 0) {
			this.y += yDir*roamSpeed;
			yDist -= roamSpeed;
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
	
	public Circle getStrikeArea() {
		return strikeArea;
	}

}

package com.spellbound.objects;

import java.awt.Rectangle;
import java.util.Random;

import com.spellbound.inventory.Item;
import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;
import com.spellbound.utils.ImageLoader;
import com.spellbound.utils.SpriteHandler;

public abstract class Hostile extends GameObject {
	
	protected boolean playerSeen;
	protected MOBSTATES state;
	
	// roaming variables
	protected Rectangle roamArea; // tracks area in which hostile can roam
	private int moveTimer, roamSpeed;	// moveTimer randomly selects time between movements
										// roamSpeed tracks hostile speed while roaming
										// chaseSpeed tracks hostile speed while chasing
	private int xDir, yDir, xDist, yDist; // Dir tracks direction of movement, dist tracks distance of movement
	
	private Random r;
	private float chaseSpeed;
	
	public Hostile(float x, float y, int width, int height, int moveDist, int roamSpeed, int damageAreaRad, int strikeAreaRad, int power, int hp) {
		super(x, y, width, height, 1, Colors.red, damageAreaRad, strikeAreaRad, power, hp);
		this.state = MOBSTATES.Roam;
		this.roamArea = new Rectangle((int)(x - moveDist), (int)(y - moveDist), moveDist*2, moveDist*2);
		this.roamSpeed = roamSpeed;
		this.chaseSpeed = 1.5f;
		
		this.r = new Random((long)(x + y));
		inv.addItem(new Item("Test", "test", 3, 64, 64, ImageLoader.loadImage("res/sprites/items/test-item.png")), 0);
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
		case Die:
			die();
			break;
		}
		
		if(hp <= 0) {
			dieTimer--;
			state = MOBSTATES.Die;
		}
		
		if(hurt) {
			hurtTimer--;
		}
		
		if(hurtTimer <= 0) {
			hurt = false;
		}
		
		currItem = inv.getItems().getFirst();
		currItem.setX(this.getCenterX());
		currItem.setY(this.getCenterY());
		
		this.move();
		
		this.setAnimation();
		this.setDirection();
		
		currAnim.run();
		
		this.tileCollide(m);
	}
	
	private void setAnimation() {
		if(state == MOBSTATES.Roam) {
			if(xDist > 0) {
				if(xDir > 0) currAnim = SpriteHandler.enemy_walkR;
				else if(xDir < 0) currAnim = SpriteHandler.enemy_walkL;
			} else if(yDist > 0) {
				if(yDir > 0) currAnim = SpriteHandler.enemy_walkD;
				else if(yDir < 0) currAnim = SpriteHandler.enemy_walkU;
			} else {
				if(xDir > 0) currAnim = SpriteHandler.enemy_idleR;
				else if(xDir < 0) currAnim = SpriteHandler.enemy_idleL;
				else if(yDir > 0) currAnim = SpriteHandler.enemy_idleD;
				else if(yDir < 0) currAnim = SpriteHandler.enemy_idleU;
			}
		} else if(state == MOBSTATES.Die) {
			if(xDir > 0) currAnim = SpriteHandler.enemy_dieR;
			else if(xDir < 0) currAnim = SpriteHandler.enemy_dieL;
			else if(yDir > 0) currAnim = SpriteHandler.enemy_dieD;
			else if(yDir < 0) currAnim = SpriteHandler.enemy_dieU;
		} else if(state == MOBSTATES.Chase) {
			if(xDir > 0) currAnim = SpriteHandler.enemy_runR;
			else if(xDir < 0) currAnim = SpriteHandler.enemy_runL;
			else if(yDir > 0) currAnim = SpriteHandler.enemy_runD;
			else if(yDir < 0) currAnim = SpriteHandler.enemy_runU;
		}
	}
	
	private void setDirection() {
		if(xDir > 0) direction = 0;
		else if(xDir < 0) direction = 4;
		else if(yDir > 0) direction = 2;
		else if(yDir < 0) direction = 6;
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
		Game.enemyChase(this);
	}
	
	public void attack() {
		Game.enemyAttack(this);
	}
	
	public void die() {
		switch(direction) {
		case 0: // east
			currAnim = SpriteHandler.enemy_dieR;
		case 2: // south
			currAnim = SpriteHandler.enemy_dieD;
		case 4: // west
			currAnim = SpriteHandler.enemy_dieL;
		case 6: // north
			currAnim = SpriteHandler.enemy_dieU;
			break;
		}
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
	
	public float getChaseSpeed() {
		return chaseSpeed;
	}

}

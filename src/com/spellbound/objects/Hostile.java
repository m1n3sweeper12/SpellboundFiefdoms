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
	
	// state variables
	protected boolean playerSeen; // if player is seen, sets to chase state
	protected MOBSTATES state; // tracks current state of mobs
	
	// roaming variables
	protected Rectangle roamArea; // tracks area in which hostile can roam
	private int moveTimer, roamSpeed;	// moveTimer randomly selects time between movements
										// roamSpeed tracks hostile speed while roaming
										// chaseSpeed tracks hostile speed while chasing
	// movement variables
	// Dir tracks direction of movement, Dist tracks distance of movement
	private int xDir, yDir, xDist, yDist;
	private boolean canMove = true;
	
	private Random r; // for random roaming directions
	private float chaseSpeed; // tracks speed while chasing player
	private int strikeCool = 0; // tracks enemy attack cooldown
	
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
		checkState();
		
		checkHealth();
		
		trackItem();
		
		if(canMove)
			move();
		
		setAnimation();
		setDirection();
		
		tileCollide(m);
	}
	
	private void checkHealth() {
		if(hp <= 0) {
			dieTimer--;
			state = MOBSTATES.Die;
			canMove = false;
		}
		
		if(hurt) {
			hurt();
		}
	}
	
	private void checkState() {
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
	}
	
	private void hurt() {
		hurtTimer--;
		
		if(hurtTimer <= 0) {
			hurt = false;
		}
	}
	
	private void trackItem() {
		currItem = inv.getItems().getFirst();
		currItem.setX(this.getCenterX());
		currItem.setY(this.getCenterY());
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
		
		currAnim.run();
	}
	
	public void setDirection() {
		if(xDir > 0) direction = 0; // east
		else if(xDir < 0) direction = 2; // west
		else if(yDir > 0) direction = 1; // south
		else if(yDir < 0) direction = 3; // north
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
		case 1: // south
			currAnim = SpriteHandler.enemy_dieD;
		case 2: // west
			currAnim = SpriteHandler.enemy_dieL;
		case 3: // north
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
	
	public int getStrikeCool() {
		return strikeCool;
	}
	
	public void setStrikeCool(int strikeCool) {
		this.strikeCool = strikeCool;
	}
	
	public void setXDir(int xDir) {
		this.xDir = xDir;
	}
	
	public void setYDir(int yDir) {
		this.yDir = yDir;
	}

}

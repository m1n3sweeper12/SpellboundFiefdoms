package com.spellbound.objects;

import java.awt.event.KeyEvent;

import com.spellbound.inventory.Item;
import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;
import com.spellbound.utils.ImageLoader;
import com.spellbound.utils.KeyManager;
import com.spellbound.utils.SpriteHandler;

public class Player extends GameObject {
	
	private int width, height;
	
	private int speed, walkSpeed, runSpeed;
	private double runStamina = 200, maxRun = 200, minRun = 100;
	private boolean canRun = true, isRunning = false, canWalk = true, isWalking = false;
	
	public Player(float x, float y, int width, int height, int strikeAreaRad) {
		super(x, y, width, height, 0, Colors.blue, Game.TILE_SIZE, strikeAreaRad, 10, 200);
		this.width = width;
		this.height = height;
		this.speed = 2;
		this.walkSpeed = 2;
		this.runSpeed = 4;
		this.direction = 0;
		
		currAnim = SpriteHandler.player_idleD;
		
		inv.addItem(new Item("Test", 3, 64, 64, ImageLoader.loadImage("res/sprites/items/test-item.png")), 0);
		inv.addItem(new Item("Test", 99, 64, 64, ImageLoader.loadImage("res/sprites/items/test-item.png")), 1);
	}
	
	@Override
	public void tick(Map m) {
		// player movement
		movePlayer();
		
		if(KeyManager.getKey(KeyEvent.VK_R)) {
			if(canAttack) {
				Game.playerAttack();
				attacking = true;
				canAttack = false;
			}
		} else {
			attacking = false;
			canAttack = true;
		}
		
		currAnim.run();
		
		// tile collisions
		if(!Game.debugMode)
			tileCollide(m);
	}
	
	private void movePlayer() {
		if(runStamina < maxRun)
			runStamina += 0.5;
		if(runStamina < 0) {
			canRun = false;
		}
		if(runStamina > minRun) {
			canRun = true;
		}
		if(KeyManager.getKey(KeyEvent.VK_SHIFT) && canRun) {
			speed = runSpeed;
			isRunning = true;
			runStamina -= 2;
		} else {
			speed = walkSpeed;
			isRunning = false;
		}
		
		if(KeyManager.getKey(KeyEvent.VK_W)) {
			this.y -= speed;
			isWalking = true;
		}
		if(KeyManager.getKey(KeyEvent.VK_S)) {
			this.y += speed;
			isWalking = true;
		}
		if(KeyManager.getKey(KeyEvent.VK_A)) {
			this.x -= speed;
			isWalking = true;
		}
		if(KeyManager.getKey(KeyEvent.VK_D)) {
			this.x += speed;
			isWalking = true;
		}
		
		if(!KeyManager.getKey(KeyEvent.VK_W) && !KeyManager.getKey(KeyEvent.VK_A) &&
				!KeyManager.getKey(KeyEvent.VK_S) && !KeyManager.getKey(KeyEvent.VK_D))
			isWalking = false;
		
		setDirection();
		
		if(isRunning) {
			setAnimation(2);
		} else if(isWalking) {
			setAnimation(1);
		} else {
			setAnimation(0);
		}
		
		this.move();
	}
	
	private void setAnimation(int mode) {
		switch(mode) {
		case 1: // walking
			if(direction == 0 || direction == 1)
				currAnim = SpriteHandler.player_walkR;
			else if(direction == 2 || direction == 3)
				currAnim = SpriteHandler.player_walkD;
			else if(direction == 4 || direction == 5)
				currAnim = SpriteHandler.player_walkL;
			else if(direction == 6 || direction == 7)
				currAnim = SpriteHandler.player_walkU;
			break;
		case 2: // running
			if(direction == 0 || direction == 1)
				currAnim = SpriteHandler.player_runR;
			else if(direction == 2 || direction == 3)
				currAnim = SpriteHandler.player_runD;
			else if(direction == 4 || direction == 5)
				currAnim = SpriteHandler.player_runL;
			else if(direction == 6 || direction == 7)
				currAnim = SpriteHandler.player_runU;
			break;
		case 0:
		default:
			if(direction == 0 || direction == 1)
				currAnim = SpriteHandler.player_idleR;
			else if(direction == 2 || direction == 3)
				currAnim = SpriteHandler.player_idleD;
			else if(direction == 4 || direction == 5)
				currAnim = SpriteHandler.player_idleL;
			else if(direction == 6 || direction == 7)
				currAnim = SpriteHandler.player_idleU;
			break;
		}
	}
	
	private void setDirection() {
		// east
		if(KeyManager.getKey(KeyEvent.VK_D) && !KeyManager.getKey(KeyEvent.VK_W) && !KeyManager.getKey(KeyEvent.VK_S)) {
			direction = 0;
		}
		// south
		else if(KeyManager.getKey(KeyEvent.VK_S) && !KeyManager.getKey(KeyEvent.VK_A) && !KeyManager.getKey(KeyEvent.VK_D)) {
			direction = 2;
		}
		// west
		else if(KeyManager.getKey(KeyEvent.VK_A) && !KeyManager.getKey(KeyEvent.VK_W) && !KeyManager.getKey(KeyEvent.VK_S)) {
			direction = 4;
		}
		// north
		else if(KeyManager.getKey(KeyEvent.VK_W) && !KeyManager.getKey(KeyEvent.VK_A) && !KeyManager.getKey(KeyEvent.VK_D)) {
			direction = 6;
		}
		// south east
		else if(KeyManager.getKey(KeyEvent.VK_S) && KeyManager.getKey(KeyEvent.VK_D)) {
			direction = 1;
		}
		// south west
		else if(KeyManager.getKey(KeyEvent.VK_S) && KeyManager.getKey(KeyEvent.VK_A)) {
			direction = 3;
		}
		// north west
		else if(KeyManager.getKey(KeyEvent.VK_W) && KeyManager.getKey(KeyEvent.VK_A)) {
			direction = 5;
		}
		// north east
		else if(KeyManager.getKey(KeyEvent.VK_W) && KeyManager.getKey(KeyEvent.VK_D)) {
			direction = 7;
		}
	}
	
	public void setY(float y) {
		this.getBounds().y = (int)y;
	}
	
	public void setX(float x) {
		this.getBounds().x = (int)x;
	}
	
	public double getRunStamina() {
		return runStamina;
	}
	
	public void setRunStamina(double runStamina) {
		this.runStamina = runStamina;
	}
	
}

package com.spellbound.objects;

import java.awt.event.KeyEvent;

import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;
import com.spellbound.utils.KeyManager;

public class Player extends GameObject {
	
	private int width, height;
	
	private int speed, walkSpeed, runSpeed;
	private double runStamina = 200, maxRun = 200, minRun = 100;
	private boolean canRun = true;//, isRunning = false;
	
	public Player(float x, float y, int width, int height, int strikeAreaRad) {
		super(x, y, width, height, 0, Colors.blue, Game.TILE_SIZE, strikeAreaRad, 10, 200);
		this.width = width;
		this.height = height;
		this.speed = 2;
		this.walkSpeed = 2;
		this.runSpeed = 4;
		this.direction = 0;
	}
	
	public void loadAnimations() {
		
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
		
		// tile collisions
		if(!Game.debugMode)
			tileCollide(m);
		
		System.out.println(canAttack);
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
			//isRunning = true;
			runStamina -= 2;
		} else {
			speed = walkSpeed;
			//isRunning = false;
		}
		
		if(KeyManager.getKey(KeyEvent.VK_W)) {
			this.y -= speed;
		}
		if(KeyManager.getKey(KeyEvent.VK_S)) {
			this.y += speed;
		}
		if(KeyManager.getKey(KeyEvent.VK_A)) {
			this.x -= speed;
		}
		if(KeyManager.getKey(KeyEvent.VK_D)) {
			this.x += speed;
		}
		
		setDirection();
		System.out.println(direction);
		
		this.move();
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

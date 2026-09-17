package com.spellbound.objects;

import java.awt.event.KeyEvent;

import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;
import com.spellbound.utils.KeyManager;

public class Player extends GameObject {
	
	private int width, height;
	
	private int speed, walkSpeed, runSpeed;
	private double runStamina = 200, maxRun = 200, minRun = 100;
	private boolean canRun = true;//, isRunning = false;
	
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height, 0, Colors.blue);
		this.width = width;
		this.height = height;
		this.speed = 2;
		this.walkSpeed = 2;
		this.runSpeed = 4;
		
	}
	
	@Override
	public void tick(Map m) {
		// player movement
		movePlayer();
		
		// tile collisions
		//tileCollide(m);
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
		
		this.move();
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

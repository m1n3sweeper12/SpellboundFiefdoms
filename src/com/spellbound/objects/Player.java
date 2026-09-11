package com.spellbound.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.tiles.Tile;
import com.spellbound.utils.Colors;
import com.spellbound.utils.KeyManager;

public class Player extends GameObject {
	
	private float x, y;
	private int width, height;
	
	private int speed, walkSpeed, runSpeed;
	private double runStamina = 200, maxRun = 200, minRun = 100;
	private boolean canRun = true, isRunning = false;
	private Rectangle topBounds, bottomBounds, leftBounds, rightBounds;
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height, 0, Colors.blue);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = 2;
		this.walkSpeed = 2;
		this.runSpeed = 4;
		this.topBounds = new Rectangle((int)(x + 5), (int)(y), width - 10, 5);
		this.bottomBounds = new Rectangle((int)(x + 5), (int)(y + height - 5), width - 10, 5);
		this.leftBounds = new Rectangle((int)(x), (int)(y + 5), 5, height - 10);
		this.rightBounds = new Rectangle((int)(x + width - 5), (int)(y + 5), 5, height - 10);
	}
	
	@Override
	public void tick() {
		// movement
		move();
	}

	public void tick(Map m) {
		// tile collisions
		for(Tile t : m.getTiles()) {
			if(topBounds.intersects(t.getBounds()) && t.isSolid()) {
				this.y = t.getBounds().y + Game.TILE_SIZE;
			}
			if(bottomBounds.intersects(t.getBounds()) && t.isSolid()) {
				this.y = t.getBounds().y - Game.TILE_SIZE;
			}
			if(leftBounds.intersects(t.getBounds()) && t.isSolid()) {
				System.out.println("got here");
				this.x = t.getBounds().x + Game.TILE_SIZE;
			}
			if(rightBounds.intersects(t.getBounds()) && t.isSolid()) {
				this.x = t.getBounds().x - Game.TILE_SIZE;
			}
		}
	}
	
	/*@Override
	public void render(Graphics2D g) {
		g.setColor(c);
		g.fill(getBounds());
		g.setColor(Colors.red);
		g.draw(topBounds);
		g.setColor(Colors.orange);
		g.draw(bottomBounds);
		g.setColor(Colors.purple);
		g.draw(leftBounds);
		g.setColor(Colors.yellow);
		g.draw(rightBounds);
	}*/
	
	private void move() {
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
		
		this.setBounds(new Rectangle((int)x, (int)y, width, height));
		this.topBounds = new Rectangle((int)(x + 5), (int)(y), width - 10, 5);
		this.bottomBounds = new Rectangle((int)(x + 5), (int)(y + height - 5), width - 10, 5);
		this.leftBounds = new Rectangle((int)(x), (int)(y + 5), 5, height - 10);
		this.rightBounds = new Rectangle((int)(x + width - 5), (int)(y + 5), 5, height - 10);
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
	
}

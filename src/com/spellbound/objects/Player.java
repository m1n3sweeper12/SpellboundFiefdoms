package com.spellbound.objects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.spellbound.inventory.Item;
import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.utils.Animation;
import com.spellbound.utils.Colors;
import com.spellbound.utils.ImageLoader;
import com.spellbound.utils.KeyManager;
import com.spellbound.utils.SpriteHandler;

public class Player extends GameObject {
	
	// movement variables
	private int speed, walkSpeed, runSpeed;
	private double runStamina = 200, maxRun = 200, minRun = 100;
	private boolean canRun = true, isRunning = false, canWalk = true, isWalking = false;
	
	private boolean canSwap = true; // tracks if inventory swapping can happen
	
	// attack variables
	private int currStrikes, maxStrikes = 5, strikeCool = 20;
	
	public Player(float x, float y, int width, int height, int strikeAreaRad) {
		super(x, y, width, height, 0, Colors.blue, Game.TILE_SIZE, strikeAreaRad, 10, 200);
		this.speed = 2;
		this.walkSpeed = 2;
		this.runSpeed = 4;
		this.direction = 0;
		
		inv.addItem(new Item("Test", "weapon", 3, 64, 64, ImageLoader.loadImage("res/sprites/items/sword-temp.png")), 0);
		inv.addItem(new Item("Test", "test", 99, 64, 64, ImageLoader.loadImage("res/sprites/items/test-item.png")), 1);
		inv.addItem(new Item("Test", "test", 30, 64, 64, ImageLoader.loadImage("res/sprites/items/test-item.png")), 1);
		currItem = inv.getItems().getFirst();
		currStrikes = 0;
		loadAnimations();
		
	}
	
	@Override
	public void tick(Map m) {
		// player movement
		movePlayer();
		
		// player attack
		attack();
		
		// hurt player if attacked
		hurt();
		
		// automatic player healing over time
		heal();
		
		// inventory slot switching
		switchInventorySlot();
		
		trackItem();
		
		//System.out.println(hp);
		
		// tile collisions
		if(!Game.debugMode)
			tileCollide(m);
	}
	
	private void hurt() {
		if(hurt) {
			hurtTimer--;
		}
		
		if(hurtTimer <= 0) {
			hurt= false;
			hurtTimer = 10;
		}
		
		if(hp <= 0) {
			dieTimer--;
		}
	}
	
	private void heal() {
		if(healTimer <= 0 && hp < maxHp) {
			hp += 2;
			healTimer = 100;
		} else {
			healTimer--;
		}
	}
	
	private void switchInventorySlot() {
		if(KeyManager.getKey(KeyEvent.VK_UP)) {
			if(canSwap) {
				inv.setHighlightedSlot(inv.getHighlightedSlot() - 1);
				// circle back to end of items if past beginning
				if(inv.getHighlightedSlot() < 0) {
					inv.setHighlightedSlot(inv.getItems().size() - 1); // set to max in inventory shown
				}
				canSwap = false;
			}
		} else if(KeyManager.getKey(KeyEvent.VK_DOWN)) {
			if(canSwap) {
				inv.setHighlightedSlot(inv.getHighlightedSlot() + 1);
				// circle back to zero if end of items
				if(inv.getHighlightedSlot() > inv.getItems().size() - 1) {
					inv.setHighlightedSlot(0);
				}
				canSwap = false;
			}
		} else {
			canSwap = true;
		}
	}
	
	private void trackItem() {
		currItem = inv.getItems().get(inv.getHighlightedSlot());
		currItem.setX(this.getCenterX());
		currItem.setY(this.getCenterY());
		switch(direction) {
		case 0:
			
		}
	}
	
	private void attack() {
		if(KeyManager.getKey(KeyEvent.VK_R)) {
			if(canAttack && currItem.getType().equals("weapon")) {
				Game.playerAttack();
				currStrikes++;
				strikeCool = 20;
				attacking = true;
				canAttack = false;
			}
		} else {
			attacking = false;
			if(currStrikes < maxStrikes && strikeCool <= 0) {
				canAttack = true;
			}
		}
		
		if(strikeCool > 0) {
			strikeCool--;
		}
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
	
	private void loadAnimations() {
		HashMap<String, Animation> anims = new HashMap<>();
		anims.put("idle-down", SpriteHandler.player_idleD);
		anims.put("idle-right", SpriteHandler.player_idleR);
		anims.put("idle-up", SpriteHandler.player_idleU);
		anims.put("idle-left", SpriteHandler.player_idleL);
		anims.put("walk-down", SpriteHandler.player_walkD);
		anims.put("walk-right", SpriteHandler.player_walkR);
		anims.put("walk-up", SpriteHandler.player_walkU);
		anims.put("walk-left", SpriteHandler.player_walkL);
		anims.put("die-down", SpriteHandler.player_dieD);
		anims.put("die-right", SpriteHandler.player_dieR);
		anims.put("die-up", SpriteHandler.player_dieU);
		anims.put("die-left", SpriteHandler.player_dieL);
		anims.put("run-down", SpriteHandler.player_runD);
		anims.put("run-right", SpriteHandler.player_runR);
		anims.put("run-up", SpriteHandler.player_runU);
		anims.put("run-left", SpriteHandler.player_runL);
		anims.put("attack-down", SpriteHandler.player_punchD);
		anims.put("attack-right", SpriteHandler.player_punchR);
		anims.put("attack-up", SpriteHandler.player_punchU);
		anims.put("attack-left", SpriteHandler.player_punchL);
		
		this.setAnimations(anims);
	}
	
	private void setAnimation(int mode) {
		switch(mode) {
		case 1: // walking
			if(direction == 0)
				currAnim = SpriteHandler.player_walkR;
			else if(direction == 1)
				currAnim = SpriteHandler.player_walkD;
			else if(direction == 2)
				currAnim = SpriteHandler.player_walkL;
			else if(direction == 3)
				currAnim = SpriteHandler.player_walkU;
			break;
		case 2: // running
			if(direction == 0)
				currAnim = SpriteHandler.player_runR;
			else if(direction == 1)
				currAnim = SpriteHandler.player_runD;
			else if(direction == 2)
				currAnim = SpriteHandler.player_runL;
			else if(direction == 3)
				currAnim = SpriteHandler.player_runU;
			break;
		case 0:
		default:
			if(direction == 0)
				currAnim = SpriteHandler.player_idleR;
			else if(direction == 1)
				currAnim = SpriteHandler.player_idleD;
			else if(direction == 2)
				currAnim = SpriteHandler.player_idleL;
			else if(direction == 3)
				currAnim = SpriteHandler.player_idleU;
			break;
		}
		
		currAnim.run();
	}
	
	@Override
	protected void setDirection() {
		// east
		if(KeyManager.getKey(KeyEvent.VK_D) && !KeyManager.getKey(KeyEvent.VK_A)) {
			direction = 0;
		}
		// south
		else if(KeyManager.getKey(KeyEvent.VK_S) && !KeyManager.getKey(KeyEvent.VK_A) && !KeyManager.getKey(KeyEvent.VK_D)) {
			direction = 1;
		}
		// west
		else if(KeyManager.getKey(KeyEvent.VK_A) && !KeyManager.getKey(KeyEvent.VK_W) && !KeyManager.getKey(KeyEvent.VK_S)) {
			direction = 2;
		}
		// north
		else if(KeyManager.getKey(KeyEvent.VK_W) && !KeyManager.getKey(KeyEvent.VK_A) && !KeyManager.getKey(KeyEvent.VK_D)) {
			direction = 3;
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

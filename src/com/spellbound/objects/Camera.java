package com.spellbound.objects;

import com.spellbound.states.Game;
import com.spellbound.tiles.Map;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player) {
		x = player.getCenterX() - Game.SCREEN_WIDTH/2;
		y = player.getCenterY() - Game.SCREEN_HEIGHT/2;
		
		if(x < 0) {
			x = 0;
		}
		
		if(x > (Map.MAP_WIDTH - Game.SCREEN_WIDTH)) {
			x = Map.MAP_WIDTH - Game.SCREEN_WIDTH;
			
		}
		
		if(y < 0) {
			y = 0;
		}

		if(y > (Map.MAP_HEIGHT - Game.SCREEN_HEIGHT)) {
			y = Map.MAP_HEIGHT - Game.SCREEN_HEIGHT;
		}
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
}

package com.spellbound.objects;

import com.spellbound.states.Game;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player) {
		x = -player.getCenterX() + Game.SCREEN_WIDTH/2;
		y = -player.getCenterY() + Game.SCREEN_HEIGHT/2;
		
		System.out.println("camera x: " + x + " y: " + y);
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

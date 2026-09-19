package com.spellbound.utils;

public class Circle {
	
	private float x, y;
	private int radius;
	
	public Circle(float x, float y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public float getArea() {
		return (float)(Math.PI*radius*radius);
	}
	
	public float getCircumference() {
		return (float)(2*Math.PI*radius);
	}
	
	public int getDiameter() {
		return 2*radius;
	}
	
}
